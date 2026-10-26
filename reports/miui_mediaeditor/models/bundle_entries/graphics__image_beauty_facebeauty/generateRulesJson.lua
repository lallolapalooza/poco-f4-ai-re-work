local cjson = require("cjson");

function Split(szFullString, szSeparator)
  local nFindStartIndex = 1
  local nSplitIndex = 1
  local nSplitArray = {}
  while true do
    local nFindLastIndex = string.find(szFullString, szSeparator, nFindStartIndex)
    if not nFindLastIndex then
      nSplitArray[nSplitIndex] = string.sub(szFullString, nFindStartIndex, string.len(szFullString))
      break
    end
    nSplitArray[nSplitIndex] = string.sub(szFullString, nFindStartIndex, nFindLastIndex - 1)
    nFindStartIndex = nFindLastIndex + string.len(szSeparator)
    nSplitIndex = nSplitIndex + 1
  end
  return nSplitArray
end

output_json = {}
linenum = 0
for line in io.lines("auto_rules4.csv") do
  if( linenum >= 1) then
    print(line)
    m = Split(line,",")
    if((#m) ~= 10) then
      print "Error"
    end
    print(m[2])
    facenum = m[2]
    age = m[3]
    gender = m[4]
    skin = m[5]
    style = m[6]
    intensity_facebeauty = m[7]
    intensity_facewarp = m[8]
    intensity_filter = m[9]
    intensity_makeup = m[10]
    
    if output_json[facenum]==nil then
      output_json[facenum] = {}
    end
    if output_json[facenum][age]==nil then
      output_json[facenum][age] = {}
    end
    if output_json[facenum][age][gender]==nil then
      output_json[facenum][age][gender] = {}
    end
    if output_json[facenum][age][gender][skin]==nil then
      output_json[facenum][age][gender][skin] = {}
    end
    output_json[facenum][age][gender][skin]["style"] = style
    output_json[facenum][age][gender][skin]["facebeauty"] = tonumber(intensity_facebeauty) / 100.0
    output_json[facenum][age][gender][skin]["facewarp"] = tonumber(intensity_facewarp) / 100.0
    output_json[facenum][age][gender][skin]["filter"] = tonumber(intensity_filter) / 100.0
    output_json[facenum][age][gender][skin]["makeup"] = tonumber(intensity_makeup) / 100.0
  end
  linenum= linenum+ 1 
end
--output_json["v"] = 1.0
--print(output_json)
json_text = cjson.encode(output_json)
print(json_text)
outfile = io.open("auto_rules.json","w")
outfile:write(json_text)
outfile:close()
