package client;

public class ThermalDecorator extends UnitDecorator {
    public ThermalDecorator(MilitaryUnit unit) {
        super(unit);
    }

    @Override
    public String getDescription() {
        return unit.getDescription() + " + Thermal Imaging";
    }

    @Override
    public double getFinalCost() {
        return unit.getFinalCost() + 250;
    }
}
