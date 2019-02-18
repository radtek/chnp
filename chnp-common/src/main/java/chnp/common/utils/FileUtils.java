package chnp.common.utils;

import java.io.File;

public class FileUtils {

	/**<p>删除指定目录下的所有文件和文件夹</p>
	 *
	 * @param dirPath 目录路径
	 * @return 删除结果。true - 删除成功
	 */
	public static boolean delContent(String dirPath) {
		File file = new File(dirPath);
		return delFile(file) && file.mkdirs();
	}

	/**<p>删除文件</p>
	 * <p>
	 *     删除指定文件。若目标是文件夹，则递归删除所有子文件、子文件夹及其本身。
	 * </p>
	 *
	 * @param file 文件文件
	 * @return 删除结果。true - 删除成功
	 */
	public static boolean delFile(File file) {
		if (null == file) throw new NullPointerException("未指定删除对象[file is null]");

		if (!file.exists()) return true;

		if (file.isDirectory()) {
			File[] fl = file.listFiles();
			if(null != fl && 0 < fl.length)
				for (File f : fl) delFile(f);
		}
		return file.delete();
	}
}