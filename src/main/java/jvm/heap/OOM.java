package jvm.heap;


/**
 * @author wenda.zhuang
 * @Date 2021/1/4 下午7:14
 * @Description -Xmx10m
 * @E-mail sis.nonacosa@gmail.com
 */
public class OOM {
	public static void main(String[] args) {
		int i = 0;
		try {
			String a = "hello";
			while (true) {
				a = a + " world";
				i++;
			}
		} catch (Throwable e){
			System.out.println(e);
			System.out.println(i);
		}
	}


}
