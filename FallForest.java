import java.util.Random;
 
class Branch 
{
    private int leaves;
    private Branch[] subBranches;
    private static Random rand = new Random();
 
    public Branch(int leaves, int subCount) 
    {
        this.leaves = leaves;
        subBranches = new Branch[subCount];
        Random rand = new Random();
        for (int i = 0; i < subCount; i++) 
        {
            subBranches[i] =
                new Branch(rand.nextInt(3) + 1, rand.nextInt(2));
        }
    }
 
    public void fallLeaves(int level) 
    {
        if (leaves <= 0 && subBranches.length == 0) return;
 
        if (leaves > 0) 
        {
            System.out.println(" ".repeat(level * 2)
                + "🍂  A leaf falls from branch level " + level);
            leaves--;
            fallLeaves(level);          // recurse on same branch
        }
 
        for (Branch b : subBranches) 
        {  // recurse into sub-branches
            b.fallLeaves(level + 1);
        }
    }

    public void growLeaves(int level) throws InterruptedException 
    {
        // Each branch regrows a few leaves
        int newLeaves = rand.nextInt(3) + 1;
        leaves += newLeaves;
        String msg = " ".repeat(level * 2) + "Branch level " + level + " grows " + newLeaves + " new leaves.";
        System.out.println(msg);
        Thread.sleep(500);

        for (Branch b : subBranches) 
        {
            b.growLeaves(level + 1);
        }
    }
}
 
public class FallForest 
{
    public static void main(String[] args) 
    {
        System.out.println("The forest prepares for autumn...");
        Branch tree = new Branch(3, 2);
        tree.fallLeaves(0);
        System.out.println("The forest sleeps for winter.");
        try
        {
            Thread.sleep(1500);
            System.out.println("\nSpring returns...");
            tree.growLeaves(0);
         }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("The forest is alive again!");
    }
}