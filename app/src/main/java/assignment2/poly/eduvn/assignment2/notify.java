package assignment2.poly.eduvn.assignment2;

/**
 * Created by vuong on 23/10/2016.
 */

public class notify  {

   UpdateListViewTK updateListViewTK;

    public void setUpdateListViewTK(UpdateListViewTK event){
        this.updateListViewTK = event;
    }

    public void handle(){
        this.updateListViewTK.handleList();
    }
}
