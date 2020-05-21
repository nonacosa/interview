package jvm;


import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.io.IOException;

/**
 * @author wenda.zhuang
 * @Date 2020/5/21 8:23 PM
 * @Description ...
 * @E-mail sis.nonacosa@gmail.com
 */
public class Attach {

	public static void main(String[] args) throws Exception {
		VirtualMachine vm = VirtualMachine.attach("69128");
		System.out.println(vm);

		//获取到所有 JVM PID
		for (VirtualMachineDescriptor descriptor : VirtualMachine.list()){

			System.out.println(descriptor.id());
			System.out.println(descriptor.displayName());
		}
	}
}
