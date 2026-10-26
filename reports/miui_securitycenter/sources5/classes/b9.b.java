package b9;

import com.miui.analytics.StatManager;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Map<String, List<a>> f5568a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a f5569b = new a(Pattern.compile(".*您本月已消费0.0元.*"));

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public a f5570c = new a(Pattern.compile(".*您的订单已出库.*"));

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a f5571d = new a(Pattern.compile(".*京东物流.*您的取件订单.*"));

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a f5572e = new a(Pattern.compile(".*京东.*购买的.*已出库.*"));

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public a f5573f = new a(Pattern.compile(".*小米.*您的访客.*接待.*"));

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public a f5574g = new a(Pattern.compile(".*微信支付.*支付[0-9.]+元.*"));

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public a f5575h = new a(Pattern.compile(".*入账工资.*人民币.*"));

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public a f5576i = new a(Pattern.compile(".*京东支付.*支付[0-9.]+元.*"));

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public a f5577j = new a(Pattern.compile("[A-Za-z0-9_]+([-+.][A-Za-z0-9_]+)*@[A-Za-z0-9_]+([-.][A-Za-z0-9_]+)*\\.(zw|zm|za|yt|ye|xyz|xxx|ws|wf|vu|vn|vi|vg|ve|vc|va|uz|uy|us|uk|ug|ua|tz|tw|tv|tt|travel|tr|top|to|tn|tm|tl|tk|tj|th|tg|tf|tel|td|tc|sz|sy|sx|sv|su|st|ss|sr|so|sn|sm|sl|sk|si|sh|sg|se|sd|sc|sb|sa|rw|ru|rs|ro|re|qa|py|pw|pt|ps|pro|pr|post|pn|pm|pl|pk|ph|pg|pf|pe|pa|org|om|nz|nu|nr|np|no|nl|ni|ng|nf|net|ne|nc|name|na|mz|my|mx|mw|mv|museum|mu|mt|ms|mr|mq|mp|moe|mobi|mo|mn|mm|ml|mk|mil|mh|mg|me|md|mc|ma|ly|lv|lu|lt|ls|lr|lk|live|li|lc|lb|la|kz|ky|kw|kr|kp|kn|km|ki|kh|kg|ke|jp|jobs|jo|jm|je|it|is|ir|iq|io|int|info|in|im|il|ie|id|hu|ht|hr|hn|hm|hk|gy|gw|guru|guitars|guide|gu|gt|gs|group|grocery|gripe|green|graphics|gr|gq|gp|gov|gop|google|golf|gold|gn|gm|global|glass|gl|gives|gifts|gift|gi|gh|gg|gf|ge|gdn|gd|gay|garden|games|game|gallery|ga|fyi|futbol|furniture|fund|fun|frontdoor|free|fr|foundation|forum|forsale|football|foodnetwork|food|foo|fo|fm|fly|flowers|florist|flights|fk|fj|fitness|fit|fishing|fish|fire|financial|finance|final|film|fi|feedback|fast|fashion|farm|fans|fan|family|faith|fail|express|exposed|expert|exchange|events|eu|et|estate|esq|es|er|equipment|enterprises|engineering|engineer|energy|email|eg|ee|education|edu|edeka|eco|ec|eat|earth|dz|duck|drive|download|dot|domains|dog|doctor|docs|do|dm|dk|dj|diy|discount|directory|direct|digital|diet|diamonds|dev|design|dentist|dental|democrat|delivery|degree|deals|deal|de|day|dating|date|data|dance|dad|cz|cy|cx|cw|cv|cu|cruises|cruise|cricket|creditcard|credit|cr|courses|coupons|coupon|country|coop|coop|cool|cooking|contractors|contact|consulting|construction|condos|computer|compare|company|community|com|college|coffee|codes|coach|co|cn|cm|club|cloud|clothing|clinic|click|cleaning|claims|cl|ck|city|circle|cipriani|ci|church|christmas|cheap|chat|channel|ch|cg|cfd|cf|cern|ceo|center|cd|cc|catholic|catering|cat|casino|cash|case|cars|careers|career|care|cards|car|capital|cancerresearch|camp|camera|cam|call|cafe|cab|ca|bz|by|bw|buzz|buy|business|builders|build|bt|bs|broker|broadway|br|box|boutique|bot|boots|book|boo|bo|bn|bm|blue|blog|blockbuster|blackfriday|black|bj|biz|bio|bingo|bike|bid|bible|bi|bh|bg|bf|bet|bestbuy|best|berlin|beer|beauty|be|bd|bb|basketball|baseball|bargains|barefoot|bar|bank|band|baby|ba|az|ax|aws|aw|autos|auto|author|audio|audible|auction|au|attorney|at|associates|asia|as|art|arpa|army|archi|ar|aq|app|apartments|ao|analytics|amazon|am|al|airforce|ai|agency|ag|africa|af|aero|ae|adult|ads|ad|actor|active|accountants|accountant|academy|ac)(?![0-9a-zA-Z@_\\-\\+\\.\\/]+)"));

    public b() {
        this.f5568a.put("10086", Arrays.asList(this.f5569b));
        this.f5568a.put("95555", Arrays.asList(this.f5575h));
        this.f5568a.put(StatManager.PARAMS_SWITCH_OFF, Arrays.asList(this.f5573f, this.f5570c, this.f5571d, this.f5572e, this.f5574g, this.f5576i, this.f5577j));
    }
}
