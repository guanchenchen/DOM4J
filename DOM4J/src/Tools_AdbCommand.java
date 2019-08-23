
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.sql.Tools_Sql;

/*
 * adb 命令方法集合类
 */
public class Tools_AdbCommand {
	/*
	 * 拍照实现方法
	 */
	public void taking_pictures() {
		try {
			Process process = Runtime.getRuntime().exec("cmd /c adb shell input keyevent 27");
			Thread.sleep(3000);
			process.destroy();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 对焦实现方法
	 */
	public void focusing() {
		try {
			Process process = Runtime.getRuntime().exec("cmd /c adb shell input keyevent 80");
			Thread.sleep(2000);
			process.destroy();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/*
	 * 删除手机照片文件方法
	 */
	public void deletecamerafile() {
		try {
			Process process = Runtime.getRuntime().exec("adb shell");
			BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
			process.getOutputStream().write("cd /mnt/sdcard/DCIM/\r\n".getBytes());
			process.getOutputStream().write("rm -r Camera/ \r\n".getBytes());
			process.getOutputStream().flush(); // 刷新流
			Thread.sleep(2000);
			input.close();
			process.destroy();
			System.out.println("手机中的照片清除完毕...");
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * pull照片的 方法
	 */
	public void pullphoto(String path, String Storage_place) {
		get_photo_name(Storage_place);
		Process process2;
		try {
			if (Storage_place == "contrast_photo") {
				process2 = Runtime.getRuntime().exec("cmd /c adb pull /mnt/sdcard/DCIM/Camera/"
						+ new Tools_Sql().getcontrast_photo_name() + "  " + path);
				Thread.sleep(3000);
				process2.destroy();
			} else if (Storage_place == "test_photo") {
				process2 = Runtime.getRuntime().exec("cmd /c adb pull /mnt/sdcard/DCIM/Camera/"
						+ new Tools_Sql().getTest_photo_name() + "  " + path);
				Thread.sleep(3000);
				process2.destroy();
			} else if (Storage_place == "error_photo") {
				process2 = Runtime.getRuntime().exec("cmd /c adb pull /mnt/sdcard/DCIM/Camera/"
						+ new Tools_Sql().getTest_photo_name() + "  " + path);
				Thread.sleep(3000);
				process2.destroy();

			}
			System.out.println("照片从手机上下载完成....");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * 得到当前测试照片名称的方法
	 */
	public void get_photo_name(String Storage_place) {
		try {
			Process process = Runtime.getRuntime().exec("adb shell");
			BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
			process.getOutputStream().write("cd /mnt/sdcard/DCIM/Camera/\r\n".getBytes());
			process.getOutputStream().write("ls\r\n".getBytes());
			process.getOutputStream().flush();
			String s = input.readLine();
			if (Storage_place == "contrast_photo") {
				new Tools_Sql().setcontrast_photo_name(s);
				System.out.println("照片名称是：" + new Tools_Sql().getcontrast_photo_name());
			} else if (Storage_place == "test_photo") {
				new Tools_Sql().setTest_photo_name(s);
				System.out.println("照片名称是：" + new Tools_Sql().getTest_photo_name());
			} else if (Storage_place == "error_photo") {
				new Tools_Sql().setTest_photo_name(s);
				System.out.println("照片名称是：" + new Tools_Sql().getTest_photo_name());

			}

			input.close();
			process.destroy();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
