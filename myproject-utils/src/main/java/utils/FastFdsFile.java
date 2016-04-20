package utils;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
/*import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerGroup;
import org.csource.fastdfs.TrackerServer;*/
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author Homing Tsang
 *
 * 2015年11月3日
 */
public class FastFdsFile {
/*
	private final Logger log = Logger.getLogger(FastFdsFile.class);

	private static FastFdsFile fastFdsFile;

	private FastFdsFile() {
		// 连接超时的时限，单位为毫秒
		ClientGlobal.setG_connect_timeout(FastfdsConstant.FILE_UPLOAD_FASTDFS_CONNECT_TIMEOUT);
		// 网络超时的时限，单位为毫秒
		ClientGlobal.setG_network_timeout(FastfdsConstant.FILE_UPLOAD_FASTDFS_NETWORK_TIMEOUT);
		ClientGlobal.setG_anti_steal_token(FastfdsConstant.FILE_UPLOAD_FASTDFS_ANTI_STEAL_TOKEN);
		// 字符集
		ClientGlobal.setG_charset(FastfdsConstant.FILE_UPLOAD_FASTDFS_CHARSET);
		ClientGlobal.setG_secret_key(FastfdsConstant.FILE_UPLOAD_FASTDFS_SECRET_KEY);
		// HTTP访问服务的端口号
		ClientGlobal.setG_tracker_http_port(FastfdsConstant.FILE_UPLOAD_FASTDFS_TRACKER_HTTP_PORT);
		// Tracker服务器列表
		InetSocketAddress[] tracker_servers = new InetSocketAddress[1];
		tracker_servers[0] = new InetSocketAddress(FastfdsConstant.FILE_UPLOAD_FASTDFS_TRACKER_SERVER, FastfdsConstant.FILE_UPLOAD_FASTDFS_TRACKER_SERVER_PORT);
		ClientGlobal.setG_tracker_group(new TrackerGroup(tracker_servers));
	}

	public static FastFdsFile getInstance() {
		if (null == fastFdsFile) {
			synchronized (FastFdsFile.class) {
				if (null == fastFdsFile) {
					fastFdsFile = new FastFdsFile();
				}
			}
		}
		return fastFdsFile;
	}

	*//**
	 * 
	 * upload(文件上传)
	 * 
	 * 
	 * @param multipartFile
	 * @return
	 * @throws IOException
	 * @throws MyException
	 * 
	 *             author：zhou'sheng
	 *//*
	public Map<String,String> upload(MultipartFile multipartFile,boolean sltflag) throws IOException, MyException {
		Map<String,String> urlMap=new HashMap<String,String>();
		if (null == multipartFile) {
			return null;
		}
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer trackerServer = trackerClient.getConnection();
		StorageServer storageServer = null;
		StorageClient1 storageClient = new StorageClient1(trackerServer, storageServer);
		String originalFilename = multipartFile.getOriginalFilename();

		String result = storageClient.upload_appender_file1(multipartFile.getBytes(), originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length()), null) + "." + multipartFile.getBytes().length;
		urlMap.put("url", result);
		if(sltflag){//生成缩略图
			result=PropertiesUtil.readValue("file.upload.fastDFS.http")+result.split("\\.")[0]+"."+result.split("\\.")[1];
			result = storageClient.upload_appender_file1(getCutPic(result), originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length()), null) + "." + multipartFile.getBytes().length;
			urlMap.put("breviary", result);	
		}
		if (null != storageServer) {
			storageServer.close();
		}

		if (null != trackerServer) {
			trackerServer.close();
		}

		return urlMap;
	}
	public byte[] getCutPic(String srcImageFile) {//缩略图代码
		  try {
			  byte[] b = new byte[4096];
			  URL url = new URL(srcImageFile);
	          BufferedImage bi = ImageIO.read(url);
	          int srcWidth = bi.getWidth();
	          int srcHeight = bi.getHeight();
	          if (srcWidth > 0 && srcHeight > 0) {
	              Image img = bi.getScaledInstance(srcWidth, srcHeight,Image.SCALE_DEFAULT);
	              ByteArrayOutputStream  out = new ByteArrayOutputStream();
	      		BufferedImage tag = new BufferedImage(240, 160, BufferedImage.TYPE_INT_RGB);
	              Graphics g = tag.getGraphics();
	              g.drawImage(img, 0, 0, 240, 160, null);
	              g.dispose();
	              ImageIO.write(tag, "JPEG", out);
	              b = out.toByteArray();
	          }
	         return b;
	      } catch (Exception e) {
	          log.error("缩略图片错误",e);
	          return null;
	      }
	}
	*//**
	 * 
	 * delete(删除文件)
	 * 
	 * @param fileId
	 * @return
	 * @throws IOException
	 * @throws MyException
	 * 
	 *             author：zhou'sheng
	 *//*
	public int delete(String fileId) throws IOException, MyException {

		TrackerClient trackerClient = new TrackerClient();
		TrackerServer trackerServer = trackerClient.getConnection();
		StorageServer storageServer = null;
		StorageClient1 storageClient = new StorageClient1(trackerServer, storageServer);

		int result = storageClient.delete_file1(fileId);

		if (null != storageServer) {
			storageServer.close();
		}

		if (null != trackerServer) {
			trackerServer.close();
		}

		return result;
	}

	public static void main(String[] args) {

		try {
			FastFdsFile.getInstance().delete("img/M00/00/00/wKgyMlQf9MmED8dXAAAAABQ0e4k889.gif");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		}
	}
*/
}
