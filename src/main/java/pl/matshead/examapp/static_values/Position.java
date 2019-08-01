package pl.matshead.examapp.static_values;

/**
 * Enum holds names of position and level of deployment
 * The lowest number, the more important position it is.
 */
public enum Position {
    EXECUTIE_CHIEF(1),
    MANAGER(2),
    TEAM_LEADER(3),
    PROGRAMMER(4),
    TESTER(5),
    SECRETARY(5),
    ;
    private Integer employmentLevel;


    public Integer getEmploymentLevel() {
        return employmentLevel;
    }

    Position(Integer employmentLevel) {
        this.employmentLevel = employmentLevel;
    }

}
