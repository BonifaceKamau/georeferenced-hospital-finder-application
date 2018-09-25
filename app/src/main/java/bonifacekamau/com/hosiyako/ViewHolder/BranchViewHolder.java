package bonifacekamau.com.hosiyako.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import bonifacekamau.com.hosiyako.R;
import bonifacekamau.com.hosiyako.interfaces.ItemClickListener;

public class BranchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView branch_name;
    public ImageView branch_image;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public BranchViewHolder(View itemView) {
        super(itemView);
        branch_name = (TextView)itemView.findViewById(R.id.branch_name);
        branch_image = (ImageView) itemView.findViewById(R.id.branch_image);



        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
