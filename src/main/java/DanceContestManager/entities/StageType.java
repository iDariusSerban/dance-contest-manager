package DanceContestManager.entities;


public enum StageType {
    Preliminary(1),
    Semifinal(2),
    Final(3);

    private final int number;

    StageType(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public static StageType fromNumber(int number) {
        for (StageType type : StageType.values()) {
            if (type.getNumber() == number) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant with number " + number);
    }

}
