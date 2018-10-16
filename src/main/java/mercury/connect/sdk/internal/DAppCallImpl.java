package mercury.connect.sdk.internal;

import mercury.connect.sdk.DAppCall;

import java.io.InputStream;
import java.io.OutputStream;

class DAppCallImpl implements DAppCall {
    @Override
    public byte[] getRelationProof() {
        return new byte[0];
    }

    @Override
    public InputStream getInputStream() {
        return null;
    }

    @Override
    public OutputStream getOutputStream() {
        return null;
    }
}
