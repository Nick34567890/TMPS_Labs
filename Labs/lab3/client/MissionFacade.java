package client;


public class MissionFacade {

    private static final double TAX_RATE = 0.10;

    public MilitaryUnit createUnit(int unitChoice, int protocolChoice) {
        DeploymentProtocol protocol = switch (protocolChoice) {
            case 1 -> new SatelliteProtocol();
            case 2 -> new RadioProtocol();
            case 3 -> new EncryptedNetworkProtocol();
            default -> throw new IllegalArgumentException("Invalid protocol");
        };

        return switch (unitChoice) {
            case 1 -> new ReconDrone(protocol);
            case 2 -> new ArmoredVehicle(protocol);
            case 3 -> new HeavyTank(protocol);
            default -> throw new IllegalArgumentException("Invalid unit");
        };
    }

    public MilitaryUnit applyUpgrade(MilitaryUnit base, int upgradeChoice) {
        return switch (upgradeChoice) {
            case 1 -> new StealthDecorator(base);
            case 2 -> new ArmorDecorator(base);
            case 3 -> new ThermalDecorator(base);
            default -> base;
        };
    }

    public double calculateFinalCost(MilitaryUnit unit, boolean elite) {
        double price = unit.getFinalCost();
        if (elite) price *= 0.9; // 10% discount
        return price + (price * TAX_RATE);
    }
}
