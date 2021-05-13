package openQuestion;

/**
 * @author wenda.zhuang
 * @Date 2021/5/13 下午7:17
 * @Description 开放性题目，IP to int
 * https://leetcode-cn.com/circle/discuss/l8fl8B/
 * @E-mail sis.nonacosa@gmail.com
 */
public class Ip2Int {
	
	public static int ip2Int(String ip) {
		String[] ips = ip.split("\\.");
		return  Integer.parseInt(ips[0]) << 24 |
				Integer.parseInt(ips[1]) << 16 |
				Integer.parseInt(ips[2]) << 8 |
				Integer.parseInt(ips[3]);
	}

	public static String int2Ip(int ipInt) {
		StringBuffer sb = new StringBuffer();

		return sb.append((ipInt >>24 & 255) + ".")
			     .append((ipInt >>16 & 255) + ".")
			     .append((ipInt >>8  & 255) + ".")
			     .append((ipInt & 255)).toString();
	}

 
	public static void main(String[] args) {
		System.out.println(ip2Int("192.168.18.152"));
		System.out.println(int2Ip(ip2Int("192.168.18.152")));
	}
}
