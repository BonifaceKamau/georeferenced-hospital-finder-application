package bonifacekamau.com.hosiyako;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import bonifacekamau.com.hosiyako.Model.Branch;

public class BranchDetail extends AppCompatActivity {

    TextView branchName,branch_phone,branch_description,branch_address,branchServices;
    ImageView branchImage;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnFind;
    ElegantNumberButton numberButton;

    String branchId="";
    FirebaseDatabase database;
    DatabaseReference branch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_detail);


        //firebase
        database = FirebaseDatabase.getInstance();
        branch = database.getReference("Branch");

        //init View
        numberButton = (ElegantNumberButton)findViewById(R.id.number_button);
        btnFind = (FloatingActionButton)findViewById(R.id.btnLocate);

        branch_description = (TextView) findViewById(R.id.branch_description);
        branchName = (TextView)findViewById(R.id.branch_name);
        branch_phone = (TextView)findViewById(R.id.branch_phone);
        branch_address = (TextView)findViewById(R.id.branch_address);
        branchServices = (TextView)findViewById(R.id.branch_services);
        branchImage = (ImageView)findViewById(R.id.img_branch);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        //Get Food Id from Intent
        if (getIntent() != null)
            branchId = getIntent().getStringExtra("BranchId");
        if(!branchId.isEmpty())
        {
            getDetailBranch(branchId);
        }

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUp =  new Intent(BranchDetail.this, MapsActivity.class);
                startActivity(signUp);

            }
        });

    }

    private void getDetailBranch(String branchId) {
        branch.child(branchId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Branch branch = dataSnapshot.getValue(Branch.class);

                //set image
                Picasso.with(getBaseContext()).load(branch.getImage()).into(branchImage);

                collapsingToolbarLayout.setTitle(branch.getName());
                branch_phone.setText(branch.getPhone());
                branchName.setText(branch.getName());
                branch_address.setText(branch.getAddress());
                branch_description.setText(branch.getDescription());
                branchServices.setText(branch.getServices());




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}
