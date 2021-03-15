package k6;

import org.apache.commons.lang3.StringUtils;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import lombok.SneakyThrows;

/**
 * @author wenda.zhuang
 * @Date 2021/3/12 下午3:30
 * @Description  拆分利用 chrome 插件录制的 k6 请求，拆分为一个一个的独立请求，过滤，静态资源，添加打印，方便单独测试，不要批量测试
 * @E-mail sis.nonacosa@gmail.com
 */
public class SplitRecordRequest {
	// your path
	public static final String K6_JS_PATH = "/Users/Venda-GM/Desktop/git-company/benchmark/project/project.js";

	public static final String REQ_SPL_SYMBOL = "},\n" + "        }\n" + "      \\);";
	public static final String FUN_START_SYMBOL = "function () {";
	public static final String GEN_REQ_TEMPLATE = "// Creator: k6 Browser Recorder 0.6.0 and java split\n\nimport { sleep, group } from \"k6\";\n" + "import http from \"k6/http\";\n"  + "\n" + "export let options = {\n" + "  vus: 100,\n" + "  duration: '300s',\n" + "};\n" + "export default function () {\n" + "  %s\n" + "\n" + "  sleep(1);\n %s" + "}";


	public static final String CONSOLE_CODE = "    let data = JSON.parse(response.body).data;\n" + "    if(data == null || data === \"undefined\") {\n" + "      console.info(\"无返回数据，无效压测！\");\n" + "    } else {\n" + "      console.info(\"本接口压测返回：\" + JSON.parse(response.body).data.length + \"条有效数据\");\n" + "    }\n" + "    ";

	@SneakyThrows
	public static void main(String[] args) {
		List<String> independent_req_list = Arrays.asList(readFileAsString(K6_JS_PATH).split(REQ_SPL_SYMBOL));
		for (int i = 0; i < independent_req_list.size(); i++) {
			String req = independent_req_list.get(i);
			if(i ==0) {
//				req = StringUtils.substringBetween(req,FUN_START_SYMBOL).concat(REQ_SPL_SYMBOL);
				req =  req.substring(StringUtils.lastIndexOf(req,FUN_START_SYMBOL)).concat("},\n" + "        }\n      );");

			}
			else if(i == independent_req_list.size() - 1) {
				continue;
			}
			else {
				req =   req.concat("},\n" + "        }\n" + "      );");
			}
			saveRequestFile(req.replace("response","var response").replace("https://www.qmenhu.com","http://localhost:10121"));
		}

	}

	public static String readFileAsString(String fileName)throws Exception
	{
		String data = "";
		data = new String(Files.readAllBytes(Paths.get(fileName)));
		return data;
	}

	@SneakyThrows
	public static void saveRequestFile(String req) {
		String req_url = StringUtils.substringBetween(req,"(\n" + "        \"","\",");
		String current_req_name = StringUtils.substring(req_url,StringUtils.lastIndexOf(req_url,"/") + 1);
		if(current_req_name.contains("?")) {
			current_req_name = current_req_name.substring(0,StringUtils.lastIndexOf(current_req_name,"?"));
		}
		if(StringUtils.isEmpty(current_req_name)) {
			return;
		}
		if(StringUtils.containsAny(current_req_name,"png","gif","jpg","css")) {
			return;
		}
		String req_file_name = current_req_name.concat(".js");
		String current_folder = StringUtils.substring(K6_JS_PATH, 0,StringUtils.lastIndexOf(K6_JS_PATH,"/"));
		String req_save_path = current_folder + "/k6-" + req_file_name;
		String fileStr = String.format(GEN_REQ_TEMPLATE,req,CONSOLE_CODE);

		FileOutputStream outputStream = new FileOutputStream(req_save_path);
		byte[] strToBytes = fileStr.getBytes();
		outputStream.write(strToBytes);
		outputStream.close();
	}
}
