import java.util.*;
/*    # Chain Marketing Organization Problem

## Problem Statement

A Chain Marketing Organization has a scheme for income generation.

* If a parent member adds child members, then:

  * Parent gets **10% commission** from each child.
  * Each child gets **5% commission**.
* If parent has no child members:

  * Parent gets only **5% commission**.
* Joining amount = **Rs.5000**

Take input:

1. Parent member name
2. Y/N whether child members exist
3. Child member names separated by comma

Display:

* Total members joined
* Commission earned by each member

---

# Approach (ArrayList Approach) 

## Algorithm

1. Read parent member name.
2. Read choice (`Y/N`).
3. If choice is `Y`:

   * Take child names separated by comma.

   * Store child names in `ArrayList`.

   * Count total members.

   * Parent commission:

     10% × 5000 × number of children

   * Each child commission:

     5% × 5000
4. Else:

   * Parent gets:

     5% × 5000
5. Print result.

---

# Time Complexity

* Splitting + Traversing children:

  O(n)

# Space Complexity

* ArrayList storage:

  O(n)

---

# Sample Input 1

```text
Amit
Y
Rajesh,Virat
```

# Sample Output 1

```text
Total Members: 3
Amit Comm: Rs.1000.0
Rajesh Comm: Rs.250.0
Virat Comm: Rs.250.0
```

---

# Sample Input 2

```text
Rohit
N
```

# Sample Output 2

```text
Total Members: 1
Rohit Comm: Rs.250.0
```

---

# Sample Input 3

```text
Karan
Y
Aman,Raj,Simran
```

# Sample Output 3

```text
Total Members: 4
Karan Comm: Rs.1500.0
Aman Comm: Rs.250.0
Raj Comm: Rs.250.0
Simran Comm: Rs.250.0
```
 */

public class MemberCommisions {

    static final int AMT = 5000;
    public static void calcComm(String par, ArrayList<String> childs) {

        int totalMem = 1 + childs.size();

        double parComm = childs.size() * (AMT * 0.10);

        System.out.println("Total Members: " + totalMem);

        System.out.println(par + " Comm: Rs." + parComm);

        for (String mem : childs) {
            double memComm = AMT * 0.05;

            System.out.println(mem + " Comm: Rs." + memComm);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String par = sc.nextLine();

        String ch = sc.nextLine();

        ArrayList<String> childs = new ArrayList<>();

        if (ch.equalsIgnoreCase("Y")) {

            String input = sc.nextLine();

            for (String mem : input.split(",")) {
                childs.add(mem.trim());
            }

            calcComm(par, childs);

        } else {

            System.out.println("Total Members: 1");

            System.out.println(par + " Comm: Rs." + (AMT * 0.05));
        }

        sc.close();
    }
}
