package com.kd.commons.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class FileUtils {
	
	@SuppressWarnings("serial")
	public final static Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>(){ {
		FILE_TYPE_MAP.put("jpg", "FFD8FF"); // JPEG (jpg)
		FILE_TYPE_MAP.put("png", "89504E47"); // PNG (png)
		FILE_TYPE_MAP.put("gif", "47494638"); // GIF (gif)
		FILE_TYPE_MAP.put("tif", "49492A00"); // TIFF (tif)
		FILE_TYPE_MAP.put("bmp", "424D"); // Windows Bitmap (bmp)
		FILE_TYPE_MAP.put("dwg", "41433130"); // CAD (dwg)
		FILE_TYPE_MAP.put("html", "68746D6C3E"); // HTML (html)
		FILE_TYPE_MAP.put("rtf", "7B5C727466"); // Rich Text Format (rtf)
		FILE_TYPE_MAP.put("xml", "3C3F786D6C");
		FILE_TYPE_MAP.put("zip", "504B0304");
		FILE_TYPE_MAP.put("rar", "52617221");
		FILE_TYPE_MAP.put("psd", "38425053"); // Photoshop (psd)
		FILE_TYPE_MAP.put("eml", "44656C69766572792D646174653A"); // Email
		// [thorough
		// only]
		// (eml)
		FILE_TYPE_MAP.put("dbx", "CFAD12FEC5FD746F"); // Outlook Express (dbx)
		FILE_TYPE_MAP.put("pst", "2142444E"); // Outlook (pst)
		FILE_TYPE_MAP.put("xls", "D0CF11E0"); // MS Word
		FILE_TYPE_MAP.put("doc", "D0CF11E0"); // MS Excel 注意：word 和
		// excel的文件头一样
		FILE_TYPE_MAP.put("mdb", "5374616E64617264204A"); // MS Access (mdb)
		FILE_TYPE_MAP.put("wpd", "FF575043"); // WordPerfect (wpd)
		FILE_TYPE_MAP.put("eps", "252150532D41646F6265");
		FILE_TYPE_MAP.put("ps", "252150532D41646F6265");
		FILE_TYPE_MAP.put("pdf", "255044462D312E"); // Adobe Acrobat (pdf)
		FILE_TYPE_MAP.put("qdf", "AC9EBD8F"); // Quicken (qdf)
		FILE_TYPE_MAP.put("pwl", "E3828596"); // Windows Password (pwl)
		FILE_TYPE_MAP.put("wav", "57415645"); // Wave (wav)
		FILE_TYPE_MAP.put("avi", "41564920");
		FILE_TYPE_MAP.put("ram", "2E7261FD"); // Real Audio (ram)
		FILE_TYPE_MAP.put("rm", "2E524D46"); // Real Media (rm)
		FILE_TYPE_MAP.put("mpg", "000001BA"); //    
		FILE_TYPE_MAP.put("mov", "6D6F6F76"); // Quicktime (mov)
		FILE_TYPE_MAP.put("asf", "3026B2758E66CF11"); // Windows Media (asf)
		FILE_TYPE_MAP.put("mid", "4D546864"); // MIDI (mid)
	}};

	/**
	 * 获取文件类型,包括图片,若格式不是已配置的,则返回null]
	 * </p>
	 * 
	 * @param file
	 * @return fileType
	 */
	public final static String getFileContentType(File file) {
		String filetype = "";
		byte[] b = new byte[50];
		try {
			InputStream is = new FileInputStream(file);
			is.read(b);
			filetype = getFileTypeByStream(b);
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filetype;
	}

	public final static String getFileTypeByStream(byte[] b) {
		String filetypeHex = String.valueOf(getFileHexString(b));
		Iterator<Entry<String, String>> entryiterator = FILE_TYPE_MAP
				.entrySet().iterator();
		while (entryiterator.hasNext()) {
			Entry<String, String> entry = entryiterator.next();
			String fileTypeHexValue = entry.getValue();
			if (filetypeHex.toUpperCase().startsWith(fileTypeHexValue)) {
				return entry.getKey();
			}
		}
		return null;
	}

	public final static String getFileHexString(byte[] b) {
		StringBuilder stringBuilder = new StringBuilder();
		if (b == null || b.length <= 0) {
			return null;
		}
		for (int i = 0; i < b.length; i++) {
			int v = b[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	

	/**
	 * 保存文件
	 */
	public static void saveFile(InputStream stream, String filePath,
			String fineName) {
		OutputStream bos = null; // 文件输出流
		try {

			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			file = new File(filePath, fineName);
			bos = new FileOutputStream(file); // 通过输出流构建文件
			int bytesRead = 0; // 阅读输入文件流的标识
			byte[] buffer = new byte[1024];
			// 读取输入流
			while ((bytesRead = stream.read(buffer, 0, buffer.length)) != -1) {
				bos.write(buffer, 0, bytesRead);// 将文件写入服务器
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bos != null)
					bos.close();
				if (stream != null)
					stream.close();
			} catch (Exception e1) {
			}
		}
	}

	public static void saveFile(InputStream stream, String filePathName) {
		OutputStream bos = null; // 文件输出流
		try {

			File file = new File(filePathName);
			if (!file.getParentFile().exists()) {
				file.mkdirs();
			}
			bos = new FileOutputStream(file); // 通过输出流构建文件
			int bytesRead = 0; // 阅读输入文件流的标识
			byte[] buffer = new byte[1024];
			// 读取输入流
			while ((bytesRead = stream.read(buffer, 0, buffer.length)) != -1) {
				bos.write(buffer, 0, bytesRead);// 将文件写入服务器
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bos != null)
					bos.close();
				if (stream != null)
					stream.close();
			} catch (Exception e1) {
			}
		}
	}

	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean delFile(String filePathName) {
		File file = new File(filePathName);
		return file.delete();
		//System.out.println("删除 " + rs + " : " + filePathName);
	}

	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 查找文件
	 * 
	 * @param destDir
	 * @param fileName
	 * @param extName
	 * @return
	 */
	public static File searchFile(String destDir, String fileName) {
		File parentFile = new File(destDir);
		File files[] = parentFile.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				File tempFile = searchFile(file.getPath(), fileName);
				if (tempFile != null) {
					return tempFile;
				}
				continue;
			}
			if (fileName.indexOf(".") > 0) {
				if (file.getName().equals(fileName)) {
					return file;
				}
			} else {
				if (file.getName()
						.substring(0, file.getName().lastIndexOf(".")).equals(
								fileName)) {
					return file;
				}
			}
		}
		return null;
	}
	
	/**
	 * come back all file below the current directory.
	 * @param dir
	 * @return
	 */
	public static List<File> getCurrentFiles(String dir) {
		if(null==dir || "".equals(dir)) {
			return null;
		}
		
		File f = new File(dir);
		if(!f.exists()) {
			return null;
		}
		List<File> list = new ArrayList<File>();
		if(f.isFile()) {
			list.add(f);
			return list;
		} else {
			File[] fs = f.listFiles();
			for(int i=0; i<fs.length; i++) {
				if(fs[i].isFile()) {
					list.add(fs[i]);
				}
			}
			return list;
		}
	}
	public static List<String> getCurrentFileNames(String dir) {
		if(null==dir || "".equals(dir)) {
			return null;
		}
		
		File f = new File(dir);
		if(!f.exists()) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		if(f.isFile()) {
			list.add(f.getName());
			return list;
		} else {
			File[] fs = f.listFiles();
			for(int i=0; i<fs.length; i++) {
				if(fs[i].isFile()) {
					list.add(fs[i].getName());
				}
			}
			return list;
		}
	}

	/**
	 * 查找文件夹所有文件
	 * 
	 * @param fileDir
	 * @return
	 */
	public static ArrayList<File> listFiles(String fileDir) {
		File file = new File(fileDir);
		return listFiles(file);
	}

	/**
	 * 查找查找文件夹所有文件
	 * 
	 * @param fileDir
	 * @return
	 */
	public static ArrayList<File> listFiles(File fileDir) {
		File files[] = fileDir.listFiles();
		ArrayList<File> fileList = new ArrayList<File>();
		if(files==null){
			return fileList;
		}
		for (File file : files) {
			if (file.isDirectory()) {
				fileList.addAll(listFiles(file));
				continue;
			}
			fileList.add(file);
		}
		return fileList;
	}

	/**
	 * 复制文件到指定的目录下面,提定文件名
	 * 
	 * @param srcFile
	 * @param destDirect
	 */
	public static void copyFile(File srcFile, File destDirect, String fileName) {
		try {
			if (!destDirect.exists()) {
				destDirect.mkdirs();
			}
			File destFile = new File(destDirect, fileName);
			FileInputStream in = new FileInputStream(srcFile);
			FileOutputStream out = new FileOutputStream(destFile);
			byte[] readByes = new byte[1024];
			while (in.read(readByes) != -1) {
				out.write(readByes);
			}
			out.close();
			in.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String getExtName(String fileName){
		return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
	}
	public static String getClassDir(){
		return FileUtils.class.getResource("/").getPath();
	}
	public static String getClassPath(){
		return FileUtils.class.getResource("").getPath();
	}
	
	/**
	 * come back all of related file path from the given folder.
	 * @param file must be absolute path
	 * @param list
	 */
	public static void getAllFileNames(String fileName, List<String> list) {
		if(null==fileName || "".equals(fileName)) {
			return ;
		}
		File file = new File(fileName);
		if(!file.exists()) {
			return ;
		}
		if(file.isFile()) {
			list.add(file.getName());
		} else if(file.isDirectory()) {
			File[] children = file.listFiles();
			for(int i=0; i<children.length; i++) {
				getAllFileNames(children[i].getPath(), list);
			}
		}
	}
	
	public static void main(String args[]){
//		System.out.println(FileUtils.getClassDir());
		File file = new File(FileUtils.class.getClassLoader().getResource("com/kd/crawler/common").getPath());
		List<String> list = new ArrayList<String>();
		getAllFileNames(file.getPath(), list);
		for(String fn : list) {
			System.out.println(fn);
		}
	}
}
