## Diagrama de classes

classDiagram
    class User {
        Long id
        String name
        Integer totalAbsences
        Integer monthAbsences
        LocalDate todayDate
        String studentImage
        Map~LocalDate, Boolean~ weeklyFrequency
        List~Absence~ absences
    }

    class Absence {
        Long id
        LocalDate absenceDay
        boolean isJustificationValid
        String justificationText
    }

    class Guardian {
        String name
        Long phone
    }

    User --> Guardian : Mother
    User --> Guardian : Father
    User --> Absence : has
