package com.mycompany.wildliferescue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Wildlife SA Rescue Operations System.
 */
public class MainTest {

    // ============================================================
    // INJURED ANIMAL RESCUE TESTS
    // ============================================================

    @Test
    public void testInjuredAnimalRescueCreation() {

        InjuredAnimalRescue rescue =
                new InjuredAnimalRescue(
                        "R001",
                        "Leo",
                        "Lion",
                        "Kruger National Park",
                        "John",
                        2,
                        1000.00,
                        "Broken leg",
                        3000.00,
                        false
                );

        assertEquals("R001", rescue.getCaseId());
        assertEquals("Leo", rescue.getAnimalName());
        assertEquals("Lion", rescue.getSpecies());
        assertEquals("Kruger National Park", rescue.getLocation());
        assertEquals("John", rescue.getRanger());
        assertEquals(2, rescue.getRescueDays());
        assertEquals(1000.00, rescue.getDailyCareCost());
        assertEquals("Broken leg", rescue.getInjuryDescription());
        assertEquals(3000.00, rescue.getVeterinaryCost());
        assertFalse(rescue.isSurgeryRequired());
    }

    @Test
    public void testInjuredAnimalPriorityWithSurgery() {

        InjuredAnimalRescue rescue =
                new InjuredAnimalRescue(
                        "R002",
                        "Simba",
                        "Lion",
                        "Pretoria",
                        "Peter",
                        3,
                        500.00,
                        "Serious injury",
                        4000.00,
                        true
                );

        assertEquals(
                RescuePriority.CRITICAL,
                rescue.determinePriority()
        );
    }

    @Test
    public void testInjuredAnimalPriorityWithHighVetCost() {

        InjuredAnimalRescue rescue =
                new InjuredAnimalRescue(
                        "R003",
                        "Max",
                        "Elephant",
                        "Limpopo",
                        "David",
                        2,
                        1000.00,
                        "Leg injury",
                        12000.00,
                        false
                );

        assertEquals(
                RescuePriority.HIGH,
                rescue.determinePriority()
        );
    }

    @Test
    public void testInjuredAnimalPriorityMedium() {

        InjuredAnimalRescue rescue =
                new InjuredAnimalRescue(
                        "R004",
                        "Bella",
                        "Leopard",
                        "Mpumalanga",
                        "James",
                        2,
                        1000.00,
                        "Minor injury",
                        3000.00,
                        false
                );

        assertEquals(
                RescuePriority.MEDIUM,
                rescue.determinePriority()
        );
    }

    @Test
    public void testInjuredAnimalTotalCost() {

        InjuredAnimalRescue rescue =
                new InjuredAnimalRescue(
                        "R005",
                        "Leo",
                        "Lion",
                        "Limpopo",
                        "John",
                        2,
                        1000.00,
                        "Broken leg",
                        3000.00,
                        true
                );

        /*
         * Daily care:
         * 2 x R1000 = R2000
         *
         * Veterinary = R3000
         *
         * Surgery = R5000
         *
         * Total = R10000
         */

        assertEquals(
                10000.00,
                rescue.calculateTotalCost(),
                0.01
        );
    }

    // ============================================================
    // ORPHANED ANIMAL RESCUE TESTS
    // ============================================================

    @Test
    public void testOrphanedAnimalCreation() {

        OrphanedAnimalRescue rescue =
                new OrphanedAnimalRescue(
                        "R006",
                        "Baby",
                        "Elephant",
                        "Kruger",
                        "Sarah",
                        4,
                        500.00,
                        2,
                        1000.00,
                        true
                );

        assertEquals("R006", rescue.getCaseId());
        assertEquals("Baby", rescue.getAnimalName());
        assertEquals(2, rescue.getAgeInMonths());
        assertEquals(1000.00, rescue.getFeedingCost());
        assertTrue(rescue.isFosterCareRequired());
    }

    @Test
    public void testOrphanedAnimalHighPriority() {

        OrphanedAnimalRescue rescue =
                new OrphanedAnimalRescue(
                        "R007",
                        "Tiny",
                        "Rhino",
                        "Limpopo",
                        "Sarah",
                        2,
                        500.00,
                        2,
                        1000.00,
                        false
                );

        assertEquals(
                RescuePriority.HIGH,
                rescue.determinePriority()
        );
    }

    @Test
    public void testOrphanedAnimalMediumPriority() {

        OrphanedAnimalRescue rescue =
                new OrphanedAnimalRescue(
                        "R008",
                        "Cub",
                        "Lion",
                        "Mpumalanga",
                        "Sarah",
                        2,
                        500.00,
                        8,
                        1000.00,
                        false
                );

        assertEquals(
                RescuePriority.MEDIUM,
                rescue.determinePriority()
        );
    }

    @Test
    public void testOrphanedAnimalLowPriority() {

        OrphanedAnimalRescue rescue =
                new OrphanedAnimalRescue(
                        "R009",
                        "Young",
                        "Zebra",
                        "Gauteng",
                        "Mike",
                        3,
                        400.00,
                        18,
                        500.00,
                        false
                );

        assertEquals(
                RescuePriority.LOW,
                rescue.determinePriority()
        );
    }

    @Test
    public void testOrphanedAnimalTotalCost() {

        OrphanedAnimalRescue rescue =
                new OrphanedAnimalRescue(
                        "R010",
                        "Baby",
                        "Elephant",
                        "Kruger",
                        "John",
                        2,
                        1000.00,
                        2,
                        1500.00,
                        true
                );

        /*
         * Daily care = 2 x R1000 = R2000
         * Feeding = R1500
         * Foster care = R2500
         *
         * Total = R6000
         */

        assertEquals(
                6000.00,
                rescue.calculateTotalCost(),
                0.01
        );
    }

    // ============================================================
    // ENDANGERED SPECIES TESTS
    // ============================================================

    @Test
    public void testEndangeredSpeciesCreation() {

        EndangeredSpeciesRescue rescue =
                new EndangeredSpeciesRescue(
                        "R011",
                        "Hope",
                        "Rhino",
                        "Limpopo",
                        "David",
                        3,
                        1000.00,
                        "Critically Endangered",
                        2000.00,
                        true
                );

        assertEquals("R011", rescue.getCaseId());
        assertEquals("Rhino", rescue.getSpecies());
        assertEquals(
                "Critically Endangered",
                rescue.getClassification()
        );
        assertEquals(2000.00, rescue.getSecurityCost());
        assertTrue(
                rescue.isSpecialistTeamRequired()
        );
    }

    @Test
    public void testCriticallyEndangeredPriority() {

        EndangeredSpeciesRescue rescue =
                new EndangeredSpeciesRescue(
                        "R012",
                        "Hope",
                        "Rhino",
                        "Limpopo",
                        "David",
                        3,
                        1000.00,
                        "Critically Endangered",
                        2000.00,
                        false
                );

        assertEquals(
                RescuePriority.CRITICAL,
                rescue.determinePriority()
        );
    }

    @Test
    public void testSpecialistTeamPriority() {

        EndangeredSpeciesRescue rescue =
                new EndangeredSpeciesRescue(
                        "R013",
                        "Max",
                        "Pangolin",
                        "Mpumalanga",
                        "Peter",
                        3,
                        1000.00,
                        "Endangered",
                        2000.00,
                        true
                );

        assertEquals(
                RescuePriority.CRITICAL,
                rescue.determinePriority()
        );
    }

    @Test
    public void testEndangeredSpeciesHighPriority() {

        EndangeredSpeciesRescue rescue =
                new EndangeredSpeciesRescue(
                        "R014",
                        "Zara",
                        "Cheetah",
                        "Limpopo",
                        "James",
                        2,
                        800.00,
                        "Endangered",
                        1500.00,
                        false
                );

        assertEquals(
                RescuePriority.HIGH,
                rescue.determinePriority()
        );
    }

    @Test
    public void testEndangeredSpeciesTotalCost() {

        EndangeredSpeciesRescue rescue =
                new EndangeredSpeciesRescue(
                        "R015",
                        "Hope",
                        "Rhino",
                        "Limpopo",
                        "David",
                        2,
                        1000.00,
                        "Critically Endangered",
                        2000.00,
                        true
                );

        /*
         * Daily care = 2 x R1000 = R2000
         * Security = R2000
         * Specialist team = R8000
         *
         * Total = R12000
         */

        assertEquals(
                12000.00,
                rescue.calculateTotalCost(),
                0.01
        );
    }

    // ============================================================
    // RESCUE STATUS TESTS
    // ============================================================

    @Test
    public void testInitialStatusIsReported() {

        InjuredAnimalRescue rescue =
                new InjuredAnimalRescue(
                        "R016",
                        "Leo",
                        "Lion",
                        "Pretoria",
                        "John",
                        2,
                        500.00,
                        "Injury",
                        1000.00,
                        false
                );

        assertEquals(
                RescueStatus.REPORTED,
                rescue.getStatus()
        );
    }

    @Test
    public void testStartRescueChangesStatus() {

        InjuredAnimalRescue rescue =
                new InjuredAnimalRescue(
                        "R017",
                        "Leo",
                        "Lion",
                        "Pretoria",
                        "John",
                        2,
                        500.00,
                        "Injury",
                        1000.00,
                        false
                );

        rescue.startRescue();

        assertEquals(
                RescueStatus.IN_PROGRESS,
                rescue.getStatus()
        );
    }

    @Test
    public void testCompleteRescueChangesStatus() {

        InjuredAnimalRescue rescue =
                new InjuredAnimalRescue(
                        "R018",
                        "Leo",
                        "Lion",
                        "Pretoria",
                        "John",
                        2,
                        500.00,
                        "Injury",
                        1000.00,
                        false
                );

        rescue.startRescue();
        rescue.completeRescue();

        assertEquals(
                RescueStatus.COMPLETED,
                rescue.getStatus()
        );
    }

    @Test
    public void testCannotCompleteReportedRescue() {

        InjuredAnimalRescue rescue =
                new InjuredAnimalRescue(
                        "R019",
                        "Leo",
                        "Lion",
                        "Pretoria",
                        "John",
                        2,
                        500.00,
                        "Injury",
                        1000.00,
                        false
                );

        assertThrows(
                IllegalStateException.class,
                rescue::completeRescue
        );
    }

    @Test
    public void testCannotStartCompletedRescue() {

        InjuredAnimalRescue rescue =
                new InjuredAnimalRescue(
                        "R020",
                        "Leo",
                        "Lion",
                        "Pretoria",
                        "John",
                        2,
                        500.00,
                        "Injury",
                        1000.00,
                        false
                );

        rescue.startRescue();
        rescue.completeRescue();

        assertThrows(
                IllegalStateException.class,
                rescue::startRescue
        );
    }

    // ============================================================
    // RESCUE MANAGER TESTS
    // ============================================================

    @Test
    public void testAddCase() {

        RescueManager manager =
                new RescueManager();

        InjuredAnimalRescue rescue =
                new InjuredAnimalRescue(
                        "R021",
                        "Leo",
                        "Lion",
                        "Pretoria",
                        "John",
                        2,
                        500.00,
                        "Injury",
                        1000.00,
                        false
                );

        manager.addCase(rescue);

        assertEquals(
                1,
                manager.getCaseCount()
        );
    }

    @Test
    public void testFindCaseById() {

        RescueManager manager =
                new RescueManager();

        InjuredAnimalRescue rescue =
                new InjuredAnimalRescue(
                        "R022",
                        "Leo",
                        "Lion",
                        "Pretoria",
                        "John",
                        2,
                        500.00,
                        "Injury",
                        1000.00,
                        false
                );

        manager.addCase(rescue);

        RescueCase found =
                manager.findById("R022");

        assertNotNull(found);

        assertEquals(
                "R022",
                found.getCaseId()
        );
    }

    @Test
    public void testFindCaseIsCaseInsensitive() {

        RescueManager manager =
                new RescueManager();

        InjuredAnimalRescue rescue =
                new InjuredAnimalRescue(
                        "R023",
                        "Leo",
                        "Lion",
                        "Pretoria",
                        "John",
                        2,
                        500.00,
                        "Injury",
                        1000.00,
                        false
                );

        manager.addCase(rescue);

        RescueCase found =
                manager.findById("r023");

        assertNotNull(found);
    }

    @Test
    public void testCaseIdExists() {

        RescueManager manager =
                new RescueManager();

        InjuredAnimalRescue rescue =
                new InjuredAnimalRescue(
                        "R024",
                        "Leo",
                        "Lion",
                        "Pretoria",
                        "John",
                        2,
                        500.00,
                        "Injury",
                        1000.00,
                        false
                );

        manager.addCase(rescue);

        assertTrue(
                manager.idExists("R024")
        );
    }

    @Test
    public void testDuplicateCaseIdNotAllowed() {

        RescueManager manager =
                new RescueManager();

        InjuredAnimalRescue rescue1 =
                new InjuredAnimalRescue(
                        "R025",
                        "Leo",
                        "Lion",
                        "Pretoria",
                        "John",
                        2,
                        500.00,
                        "Injury",
                        1000.00,
                        false
                );

        InjuredAnimalRescue rescue2 =
                new InjuredAnimalRescue(
                        "R025",
                        "Simba",
                        "Lion",
                        "Limpopo",
                        "Peter",
                        3,
                        600.00,
                        "Leg injury",
                        2000.00,
                        false
                );

        manager.addCase(rescue1);

        assertThrows(
                IllegalArgumentException.class,
                () -> manager.addCase(rescue2)
        );
    }

    @Test
    public void testUpdateStatus() {

        RescueManager manager =
                new RescueManager();

        InjuredAnimalRescue rescue =
                new InjuredAnimalRescue(
                        "R026",
                        "Leo",
                        "Lion",
                        "Pretoria",
                        "John",
                        2,
                        500.00,
                        "Injury",
                        1000.00,
                        false
                );

        manager.addCase(rescue);

        boolean result =
                manager.updateStatus(
                        "R026",
                        RescueStatus.IN_PROGRESS
                );

        assertTrue(result);

        assertEquals(
                RescueStatus.IN_PROGRESS,
                rescue.getStatus()
        );
    }

    @Test
    public void testUpdateStatusForUnknownCase() {

        RescueManager manager =
                new RescueManager();

        boolean result =
                manager.updateStatus(
                        "UNKNOWN",
                        RescueStatus.COMPLETED
                );

        assertFalse(result);
    }

    @Test
    public void testTotalEstimatedCost() {

        RescueManager manager =
                new RescueManager();

        InjuredAnimalRescue rescue1 =
                new InjuredAnimalRescue(
                        "R027",
                        "Leo",
                        "Lion",
                        "Pretoria",
                        "John",
                        2,
                        1000.00,
                        "Injury",
                        2000.00,
                        false
                );

        OrphanedAnimalRescue rescue2 =
                new OrphanedAnimalRescue(
                        "R028",
                        "Baby",
                        "Elephant",
                        "Kruger",
                        "Sarah",
                        2,
                        1000.00,
                        2,
                        1500.00,
                        false
                );

        manager.addCase(rescue1);
        manager.addCase(rescue2);

        /*
         * Rescue 1:
         * 2 x R1000 + R2000 = R4000
         *
         * Rescue 2:
         * 2 x R1000 + R1500 = R3500
         *
         * Total = R7500
         */

        assertEquals(
                7500.00,
                manager.getTotalEstimatedCost(),
                0.01
        );
    }

    // ============================================================
    // VALIDATION TESTS
    // ============================================================

    @Test
    public void testBlankCaseIdNotAllowed() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new InjuredAnimalRescue(
                        "",
                        "Leo",
                        "Lion",
                        "Pretoria",
                        "John",
                        2,
                        500.00,
                        "Injury",
                        1000.00,
                        false
                )
        );
    }

    @Test
    public void testNegativeDailyCostNotAllowed() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new InjuredAnimalRescue(
                        "R029",
                        "Leo",
                        "Lion",
                        "Pretoria",
                        "John",
                        2,
                        -500.00,
                        "Injury",
                        1000.00,
                        false
                )
        );
    }

    @Test
    public void testZeroRescueDaysNotAllowed() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new InjuredAnimalRescue(
                        "R030",
                        "Leo",
                        "Lion",
                        "Pretoria",
                        "John",
                        0,
                        500.00,
                        "Injury",
                        1000.00,
                        false
                )
        );
    }

    @Test
    public void testBlankSpeciesNotAllowed() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new InjuredAnimalRescue(
                        "R031",
                        "Leo",
                        "",
                        "Pretoria",
                        "John",
                        2,
                        500.00,
                        "Injury",
                        1000.00,
                        false
                )
        );
    }

    // ============================================================
    // SUMMARY TEST
    // ============================================================

    @Test
    public void testGenerateSummary() {

        InjuredAnimalRescue rescue =
                new InjuredAnimalRescue(
                        "R032",
                        "Leo",
                        "Lion",
                        "Pretoria",
                        "John",
                        2,
                        500.00,
                        "Broken leg",
                        1000.00,
                        false
                );

        String summary =
                rescue.generateSummary();

        assertTrue(
                summary.contains("R032")
        );

        assertTrue(
                summary.contains("Injured Animal")
        );

        assertTrue(
                summary.contains("Lion")
        );

        assertTrue(
                summary.contains("John")
        );

        assertTrue(
                summary.contains("Broken leg")
        );
    }
}