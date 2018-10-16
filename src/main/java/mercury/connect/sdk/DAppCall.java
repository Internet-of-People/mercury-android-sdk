package mercury.connect.sdk;

import java.io.InputStream;
import java.io.OutputStream;

public interface DAppCall {
    byte[] getRelationProof();

    InputStream getInputStream();

    OutputStream getOutputStream();
}
