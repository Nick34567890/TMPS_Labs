package client;


public class StealthDecorator extends UnitDecorator {
    public StealthDecorator(MilitaryUnit unit) {
        super(unit);
    }

    @Override
    public String getDescription() {
        return unit.getDescription() + " + Stealth Coating";
    }

    @Override
    public double getFinalCost() {
        return unit.getFinalCost() + 300;
    }
}
