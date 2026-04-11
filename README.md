# PR2 - System Issues Management

## Author
- name: **A. César Flores Carrera**
- e-mail: **afloresca@uoc.edu**

## Solution
*In this section, all the modifications, and/or updates made to the initially proposed design, according to the official CAA1 solution, will be described
along with its justification, additional data structure, hints on the new test suites, problems and comments*

### Exception Definitions
The following exception classes has been created. All of them inherit from DSException
- ComponentAlreadyInstalledException.java
- ComponentNotFoundException.java
- IssueAlreadyAssignedException.java
- IssueAlreadyResolvedException.java
- IssueNotFoundException.java
- NoIssuesException.java
- NoSystemsException.java
- NoWorkerException.java
- SystemHasNoComponentsException.java
- WorkerNotFoundException.java

### Implementation of necessary model classes
The following model classes have been implemented in package uoc.ds.pr.model
to match the attributes and methods needed to their creation.

- Component.java
- Issue.java
- System.java
- Worker.java

I've added Lombok dependency to create getters/setters automatically.

### Implementation of _SystemIssuesPR2Impl_ class



## Overview

A computer system issue management system that enables the administration of workers, systems, components, and issues. The system facilitates task assignment to workers using a LIFO stack structure and provides queries about the overall system state.

## Main Project Components

### 1. **ADT Contract: SystemIssues.java** (`src/main/java/uoc/ds/pr/pr2/SystemIssues.java`)

Interface that defines all operations of the issue management system. Specifies the contract that any class managing issues must implement. Includes:

### 2. **Implementation: SystemIssuesPR2Impl.java** (`src/main/java/uoc/ds/pr/pr2/SystemIssuesPR2Impl.java`)

**Class to create** that implements the `SystemIssues` interface. This class is responsible for all system management logic.

**Mandatory Data Structures to Use (from DSLib/CAA1/PAC1/PEC1)**

**Main Responsibilities:**
- Maintain records of workers, systems, components, and issues
- Manage relationships: components → systems, issues → components
- Implement LIFO issue assignment operations
- Calculate statistics: top worker, system with most components
- Validate preconditions and throw appropriate exceptions

### 3. **Helper Interface: SystemIssuesHelper.java** (`src/main/java/uoc/ds/pr/pr2/SystemIssuesHelper.java`)

Interface that provides auxiliary query and counting operations:

- `getWorker(String id)` - Gets a worker by ID
- `numWorkers()` - Total number of workers
- `getSystem(String id)` - Gets a system by ID
- `numSystems()` - Total number of systems
- `getComponent(String id)` - Gets a component by ID
- `numComponents()` - Total number of components
- `numComponentsBySystem(String systemId)` - Components of a specific system
- `numIssues()` - Total number of issues
- `numIssuesByComponent(String componentId)` - Issues of a component
- `numIssuesByWorker(String workerId)` - Issues assigned to a worker

### 4. **Data Models** (`src/main/java/uoc/ds/pr/model/`)

- **Worker.java** - Represents a worker with ID, name, and address
- **System.java** - Represents a system with ID, description, and location
- **Component.java** - Represents a component with ID, trademark, model, and serial number
- **Issue.java** - Represents an issue with ID, description, creation date, and status

### 5. **Custom Exceptions** (`src/main/java/uoc/ds/pr/exceptions/`)

- `DSException` - Base exception of the system
- `ComponentNotFoundException` - When a component is not found
- `ComponentAlreadyInstalledException` - When a component is already installed
- `IssueNotFoundException` - When an issue is not found
- `IssueAlreadyAssignedException` - When the issue is already assigned
- `IssueAlreadyResolvedException` - When the issue is already resolved
- `WorkerNotFoundException` - When a worker is not found
- `NoIssuesException` - When there are no available issues
- `NoWorkerException` - When there are no registered workers
- `NoSystemsException` - When there are no registered systems
- `SystemHasNoComponentsException` - When a system has no components

## Implemented Test Classes

### 1. **ArchitectureTest.java** (`src/test/java/uoc/ds/pr/ArchitectureTest.java`)
- Validates that prohibited `java.util` classes are NOT used (ArrayList, HashMap, Stack, etc.)
- Ensures the project exclusively uses DSLib data structures
- Test: `shouldNotUseProhibitedJavaUtilClasses()`

### 2. **SystemIssuesPR2Test.java** (`src/test/java/uoc/ds/pr/SystemIssuesPR2Test.java`)
Comprehensive unit test suite that validates all system operations:

**Entity Management Tests:**
- `addWorkerTest()` - Tests addition and update of workers
- `addSystemTest()` - Tests addition and update of systems
- `addComponentTest()` - Tests addition and update of components

**Relationship Tests:**
- `installComponentToSystemTest()` - Tests component installation in systems, validating duplicates

**Issue Tests:**
- `createIssueTest()` - Tests creation of issues associated with components
- `assignIssueTest()` - Tests LIFO assignment of issues to workers
- `solveIssueTest()` - Tests resolution of issues (stack pop)

**Query Tests:**
- `getSystemsTest()` - Tests iteration over all systems
- `getComponentsBySystemTest()` - Tests retrieval of components by system
- `getDoneIssuesByWorkerTest()` - Tests retrieval of resolved issues
- `getTopWorkerTest()` - Tests retrieval of the most productive worker
- `getSystemWithMostComponentsTest()` - Tests retrieval of system with most components

### 3. **FactorySystemIssues.java** (`src/test/java/uoc/ds/pr/FactorySystemIssues.java`)
- Factory class that instantiates `SystemIssues` implementations
- Method: `getComputerProjects()` returns an instance of `SystemIssuesPR2Impl`

### 4. **StackLinkedListTest.java** (`src/test/java/uoc/ds/pr/util/StackLinkedListTest.java`)
- Unit tests for the Stack structure
- Tests: `addTest()`, `addPopTest()`, `addPeekTest()`

### 5. **Test Utilities** (`src/test/java/uoc/ds/pr/util/`)
- **CSVUtil.java** - Loads test data from CSV files (workers, systems, components, issues, assignments)
- **DateUtils.java** - Utilities for parsing dates and datetimes

## Test Data (CSV)

The project includes CSV files with test data in `src/test/resources/`:
- **workers.csv** - 28 workers (WKR3-WKR30)
- **systems.csv** - 10 systems (SYS01-SYS10)
- **components.csv** - 20 components (COMP01-COMP20)
- **installComponents2System.csv** - Component↔system mappings (20 installations)
- **issues.csv** - 34 issues with dates and descriptions
- **issue_assignments.csv** - Assignments of issues to workers

## Technologies and Dependencies

- **Java 23** - Programming language
- **Maven** - Dependency management and build tool
- **DSLib 2.1-7** - Data structures library (Stack, Iterator, etc.)
- **JUnit 4.13** - Testing framework
- **Apache Commons CSV 1.10.0** - CSV file processing
- **ArchUnit 1.3.0** - Architecture testing
- **SLF4J 2.0.13** - Logging

## Architectural Constraints

⚠️ **MANDATORY:** No classes from `java.util` are allowed to be used:
- ❌ ArrayList, LinkedList, Vector
- ❌ HashMap, Hashtable, HashSet
- ❌ TreeSet, PriorityQueue
- ❌ Stack, Dictionary, Collections

✅ **MANDATORY:** Exclusively use DSLib structures (DSLib-2.1-7.jar)



