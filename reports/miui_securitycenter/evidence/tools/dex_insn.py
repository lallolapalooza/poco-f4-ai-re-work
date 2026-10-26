#!/usr/bin/env python3
"""Minimal DEX instruction-width table + linear decoder."""
from __future__ import annotations


def _build() -> list[int]:
    w = [1] * 256
    w[0x00] = 1
    w[0x02] = 2; w[0x03] = 3
    w[0x05] = 2; w[0x06] = 3
    w[0x08] = 2; w[0x09] = 3
    for op in (0x13, 0x15, 0x16, 0x19, 0x1A, 0x1C, 0x1F, 0x20, 0x22, 0x23):
        w[op] = 2
    for op in (0x14, 0x17, 0x1B, 0x24, 0x25, 0x26, 0x2A, 0x2B, 0x2C):
        w[op] = 3
    w[0x18] = 5
    w[0x29] = 2
    for op in range(0x2D, 0x3E):
        w[op] = 2
    for op in range(0x44, 0x6E):
        w[op] = 2
    for op in range(0x6E, 0x73):
        w[op] = 3
    for op in range(0x74, 0x79):
        w[op] = 3
    for op in range(0x90, 0xB0):
        w[op] = 2
    for op in range(0xD0, 0xE3):
        w[op] = 2
    w[0xFA] = 4; w[0xFB] = 4; w[0xFC] = 3; w[0xFD] = 3
    w[0xFE] = 2; w[0xFF] = 2
    return w


WIDTH = _build()


def walk(ins: bytes):
    """Yield (unit_offset, opcode) for each instruction start."""
    i = 0
    n = len(ins)
    while i + 1 < n:
        op = ins[i]
        yield i, op
        i += 2 * WIDTH[op]
