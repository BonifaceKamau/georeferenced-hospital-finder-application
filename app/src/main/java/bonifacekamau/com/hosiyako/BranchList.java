package bonifacekamau.com.hosiyako;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import bonifacekamau.com.hosiyako.Model.Branch;
import bonifacekamau.com.hosiyako.ViewHolder.BranchViewHolder;
import bonifacekamau.com.hosiyako.interfaces.ItemClickListener;

public class BranchList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference branchList;

    String hospitalId="";

    FirebaseRecyclerAdapter<Branch,BranchViewHolder> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_list);


        //Firebase
        database = FirebaseDatabase.getInstance();
        branchList = database.getReference("Branch");

        recyclerView = (RecyclerView)findViewById(R.id.recycler_branch);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Get Intent here
        if (getIntent() != null)
            hospitalId = getIntent().getStringExtra("HospitalId");
        if (!hospitalId.isEmpty() && hospitalId != null)
        {
            loadListFood(hospitalId);
        }



    }

    private void loadListFood(String hospitalId) {
        adapter = new FirebaseRecyclerAdapter<Branch, BranchViewHolder>(Branch.class,
                R.layout.branch_item,
                BranchViewHolder.class,
                branchList.orderByChild("MenuId")
                        .equalTo(hospitalId)//select * from branches where menuID =
        ) {
            @Override
            protected void populateViewHolder(BranchViewHolder viewHolder, Branch model, int position) {

                viewHolder.branch_name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.branch_image);
                final Branch local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                       //start  New Activity
                        Intent branchDetail = new Intent(BranchList.this,BranchDetail.class);
                       branchDetail.putExtra("BranchId",adapter.getRef(position).getKey());//Send branch Id to new activity
                        startActivity(branchDetail);
                    }
                });

            }
        };
        //Set Adapter
        recyclerView.setAdapter(adapter);
    }
}
