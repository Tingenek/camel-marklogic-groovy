/**
 -- MarkLogic/Groovy/Camel Demo 
 -- 15 April 2014
 -- Mark Lawson (tingenek@gmail.com) for llamas everywhere.
 */

import org.apache.camel.Exchange;
import java.io.IOException;
import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.document.DocumentDescriptor;
import com.marklogic.client.io.InputStreamHandle;
import com.marklogic.client.DatabaseClientFactory.Authentication;
import com.marklogic.client.document.XMLDocumentManager;

class MarkTest {

	private String host;
	private int port;
	private String user;
	private String password;

	/* Setters for params */
	public void setHost(String _host) {
		this.host = _host;
	}

	public void setPort(int _port) {
		this.port = _port;
	}

	public void setUser(String _user) {
		this.user = _user;
	}

	public void setPassword(String _password) {
		this.password = _password;
	}

		public void upload_xml(Exchange exchange) {
		String docId = exchange.getIn().getHeader("ml_doc");
		if (docId == null) docId = "/twitter/" + exchange.getExchangeId();
		// create the client
		DatabaseClient client = DatabaseClientFactory.newClient(host, port,
				user, password,
				Authentication.DIGEST);
		// try to make use of the client connection
		try {
			XMLDocumentManager XMLDocMgr = client.newXMLDocumentManager();
			//Get an InputStream from the Message Body
			InputStreamHandle handle = new InputStreamHandle(exchange.getIn().getBody(InputStream.class));
			// Write out the XML Doc
			DocumentDescriptor desc = XMLDocMgr.write(docId,handle);
			//System.out.println("Wrote : " + docId + " to Marklogic." );
		} catch (Exception e) {
			System.out.println("Exception : " + e.toString() );
		} finally {
			// release the client
			client.release();
		}
	}
	
	
}
