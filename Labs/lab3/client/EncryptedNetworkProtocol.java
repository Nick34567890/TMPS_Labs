package client;

public class EncryptedNetworkProtocol implements DeploymentProtocol {
    @Override
    public String getProtocolName() {
        return "Encrypted Network";
    }

    @Override
    public double getProtocolCost() {
        return 500;
    }
}

