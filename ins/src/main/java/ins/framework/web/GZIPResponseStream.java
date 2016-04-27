package ins.framework.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

class GZIPResponseStream extends ServletOutputStream {
	private ByteArrayOutputStream baos = null;
	private GZIPOutputStream gzipstream = null;
	private boolean closed = false;
	private HttpServletResponse response = null;
	private ServletOutputStream output = null;

	public GZIPResponseStream(HttpServletResponse response) throws IOException {
		this.closed = false;
		this.response = response;
		this.output = response.getOutputStream();
		this.baos = new ByteArrayOutputStream();
		this.gzipstream = new GZIPOutputStream(this.baos);
	}

	public void close() throws IOException {
		if (this.closed) {
			throw new IOException("This output stream has already been closed");
		}
		this.gzipstream.finish();
		byte[] bytes = this.baos.toByteArray();
		this.response.setContentLength(bytes.length);
		this.response.addHeader("Content-Length",
				Integer.toString(bytes.length));
		this.response.addHeader("Content-Encoding", "gzip");
		this.output.write(bytes);
		this.output.flush();
		this.output.close();
		this.closed = true;
	}

	public void flush() throws IOException {
		if (this.closed) {
			throw new IOException("Cannot flush a closed output stream");
		}
		this.gzipstream.flush();
	}

	public void write(int b) throws IOException {
		if (this.closed) {
			throw new IOException("Cannot write to a closed output stream");
		}
		this.gzipstream.write((byte) b);
	}

	public void write(byte[] b) throws IOException {
		if (b != null)
			write(b, 0, b.length);
	}

	public void write(byte[] b, int off, int len) throws IOException {
		if (this.closed) {
			throw new IOException("Cannot write to a closed output stream");
		}
		if (b != null)
			this.gzipstream.write(b, off, len);
	}

	public boolean closed() {
		return this.closed;
	}

	public void reset() {
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.web.GZIPResponseStream JD-Core Version: 0.5.4
 */