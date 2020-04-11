import java.io.PrintWriter;
import java.lang.String;

/**
 * Implementation of the Runqueue interface using an Ordered Array.
 *
 * Your task is to complete the implementation of this class.
 * You may add methods and attributes, but ensure your modified class compiles and runs.
 *
 * @author Sajal Halder, Minyi Li, Jeffrey Chan
 * @studentAuthor Matthew Duong s3784450, Alan Lam s3436174
 */
public class OrderedArrayRQ implements Runqueue {
	
	private Proc[] procArray;
	//Front = 0, Back = index of next available array index (back = 5000 means that it is one above the set limit of elements)
	private int front, back, cap;
	
    /**
     * Constructs empty queue
     */
    public OrderedArrayRQ() {
    	cap = 5000;
    	procArray = new Proc[cap];
    	front = back = 0;

    }  // end of OrderedArrayRQ()


    @Override
    public void enqueue(String procLabel, int vt) {
        //Checking if procArray is full or not
    	if(back == 0)
    	{
    		procArray[back] = new Proc(procLabel, vt);
    		back++;
    	}
    	else if(back >= cap)
    	{
    		int oldLength = procArray.length;
    		Proc[] newProcArray = new Proc[oldLength + (oldLength/10)]; //Increase array by 10%
    		System.arraycopy(procArray, 0, newProcArray, 0, back);
    		procArray = newProcArray;
    	}
    	else if(findProcess(procLabel) == false)
    	{
    		for(int i = 0; i < back; i++)
    		{
    			if(procArray[i].getVt() > vt) //Follows FIFO as any new process with same vt as another will be placed behind it
    			{
    				for(int j = back - 1; j >= i; j--)
    				{
    					procArray[j + 1] = procArray[j];
    				}
    				procArray[i] = null;
    				procArray[i] = new Proc(procLabel, vt);
    				back++;
    				return;
    			}
    		}
    		//New process has largest vt or is another process with largest vt - Place at end of procArray
    		procArray[back] = new Proc(procLabel, vt);
    		back++;
    	}
    } // end of enqueue()


    @Override
    public String dequeue() {
    	String output;
    	
        if(arrayIsEmpty())
        {
        	output = "Error: No processes to dequeue";
        }
        else if(back == 1)
        {
        	output = procArray[0].getProcLabel();
        	procArray[0] = null;
        	back--;
        }
        else
        {
        	output = procArray[0].getProcLabel();
        	for(int i = 0; i < back - 1; i++)
        	{
        		procArray[i] = procArray[i + 1];
        	}
        	procArray[back] = null;
        	back--;
        }
        
        return output;
    } // end of dequeue()


    @Override
    public boolean findProcess(String procLabel){
    	if(arrayIsEmpty())
    	{
    		return false;
    	}
    	
    	//As array is sorted in vt, have to linear search
    	for(int i = 0; i < back; i++)
    	{
    		if(procArray[i].getProcLabel().equals(procLabel))
    		{
    			return true;
    		}
    	}
    	
    	//Nothing found, returning false
        return false;
    } // end of findProcess()


    @Override
    public boolean removeProcess(String procLabel) {
    	if(arrayIsEmpty())
    	{
    		return false;
    	}
    	
    	else if(procArray[back - 1].getProcLabel().equals(procLabel))
        {
        	procArray[back - 1] = null;
        	back--;
        	return true;
        }
    	else
    	{
        	//Again, linear search as sorted in vt
            for(int i = 0; i < back - 2; i++)
            {
            	if(procArray[i].getProcLabel().equals(procLabel))
            	{
            		for(int j = i; j < back - 1; j++)
            		{
            			procArray[j] = procArray[j + 1];
            		}
            		procArray[back - 1] = null;
            		back--;
            		return true;
            	}
            }
    	}


        
        //Process label was not found
        return false;
    } // end of removeProcess()


    @Override
    public int precedingProcessTime(String procLabel) {
    	int total = 0;
    	
    	if(arrayIsEmpty())
    	{
    		total = -1;
    	}
    	else
    	{
    		boolean labelInArray = false;
        	for(int i = 0; i < back; i++)
        	{
        		if(procArray[i].getProcLabel().equals(procLabel))
        		{
        			labelInArray = true;
        			break;
        		}
        		else
        		{
        			total += procArray[i].getVt();
        		}
        	}
        	if(!labelInArray)
        	{
        		total = -1;
        	}
    	}
    	
        return total;
    }// end of precedingProcessTime()


    @Override
    public int succeedingProcessTime(String procLabel) {
    	int total = 0;
    	
    	if(arrayIsEmpty())
    	{
    		total = -1;
    	}
    	else
    	{
    		boolean labelInArray = false;
        	for(int i = back - 1; i >= 0; i--)
        	{
        		if(procArray[i].getProcLabel().equals(procLabel))
        		{
        			labelInArray = true;
        			break;
        		}
        		else
        		{
        			total += procArray[i].getVt();
        		}
        	}
        	if(!labelInArray)
        	{
        		total = -1;
        	}
    	}
    	
        return total;
    } // end of precedingProcessTime()

    
    @Override
    public void printAllProcesses(PrintWriter os) {
    	String processes = "";
    	
        if(arrayIsEmpty())
        {
        	processes += "Error: No processes to print";
        }
        else
        {
        	for(int i = 0; i <= back - 2; i++)
        	{
        		processes += procArray[i].getProcLabel() + " ";
        	}
        	processes += procArray[back - 1].getProcLabel();
        }
        
        os.println(processes);
    } // end of printAllProcesses()
    
    public boolean arrayIsEmpty()
    {
    	if(front == back)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }

} // end of class OrderedArrayRQ
