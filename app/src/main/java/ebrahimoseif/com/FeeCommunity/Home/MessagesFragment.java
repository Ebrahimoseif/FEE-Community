package ebrahimoseif.com.FeeCommunity.Home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;

import ebrahimoseif.com.FeeCommunity.Profile.ProfileActivity;
import ebrahimoseif.com.FeeCommunity.R;
import ebrahimoseif.com.FeeCommunity.Search.SearchActivity;
import ebrahimoseif.com.FeeCommunity.Utils.BottomNavigationViewHelper;
import ebrahimoseif.com.FeeCommunity.Utils.UserListAdapter;
import ebrahimoseif.com.FeeCommunity.models.User;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by Android App team to cloud university graduation project.
 */

public class MessagesFragment extends Fragment {
    private static final String TAG = "MessagesFragment";



    // widgets
    private  ImageView msgback;
    private  EditText mSearchParam;
    
    private ImageView mSearchImage;
    //vars
    private List<User> mUserList;
    private UserListAdapter mAdapter;
    private ListView mListView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messages, container, false);

        mSearchParam = (EditText) view.findViewById(R.id.search);
        mListView = (ListView) view.findViewById(R.id.listView);
        mSearchImage= (ImageView) view.findViewById(R.id.searchImage);

        Log.d(TAG, "onCreateView: MESSAGE STARTED ");

      //  hideSoftKeyboard();
        // setupBottomNavigationView();
        searchListener();
        
        return view;
    }


    /**
     * Created by Android App team to cloud university graduation project.
     */
    


        /**
         private ArrayAdapter<String> getUsernameAdapter(Context context) {
         final ArrayList<String> usernames = new ArrayList<>();

         DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
         Query query = reference.child(getString(R.string.dbname_users))
         .orderByChild(getString(R.string.field_username)); // .equalTo(keyword);
         query.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
        for(DataSnapshot singleSnapshot :  dataSnapshot.getChildren()){
        usernames.add(singleSnapshot.getValue(User.class).getUsername());
        }
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
        });
         return new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, usernames);
         }
         */







        /**
         private void initTextListener(){
         Log.d(TAG, "initTextListener: initializing");

         mUserList = new ArrayList<>();

         mSearchParam.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        String text = mSearchParam.getText().toString().toLowerCase(Locale.getDefault());
        searchForMatch(text);
        }
        });
         }

         */

        private void searchListener(){

            Log.d(TAG, "searchListener: get serach words");

            mUserList = new ArrayList<>();

            mSearchImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String text =  mSearchParam.getText().toString();


                    Toast.makeText(getActivity(), TAG + "you searched for " +text , Toast.LENGTH_LONG).show();
                    searchForMatch(text);

                }
            });
        }

        private void searchForMatch(final String keyword){
            Log.d(TAG, "searchForMatch: searching for a match: " + keyword);
            mUserList.clear();
            //update the users list view
            if(keyword.length() ==0){
                Toast.makeText(getActivity() ,TAG + "Sorry no match found ", Toast.LENGTH_LONG).show();
            }else{
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                Query query = reference.child(getString(R.string.dbname_users))
                        .orderByChild(getString(R.string.field_username)); // .equalTo(keyword);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot singleSnapshot :  dataSnapshot.getChildren()){
                            if ( singleSnapshot.getValue(User.class).getUsername().equals(keyword))
                            {
                                Log.d(TAG, "onDataChange: found user:" + singleSnapshot.getValue(User.class).toString());
                                Toast.makeText(getActivity() , TAG + "you searched for " +singleSnapshot.getValue(User.class).toString()
                                        , Toast.LENGTH_LONG).show();

                                mUserList.add(singleSnapshot.getValue(User.class));
                                //update the users list view
                                updateUsersList();
                            }
                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }

        private void updateUsersList(){
            Log.d(TAG, "updateUsersList: updating users list");

            mAdapter = new UserListAdapter(getActivity(), R.layout.layout_user_listitem, mUserList);

            mListView.setAdapter(mAdapter);

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d(TAG, "onItemClick: selected user: " + mUserList.get(position).toString());

                    //navigate to profile activity
                    Intent intent =  new Intent(getActivity(), ProfileActivity.class);
                    intent.putExtra(getString(R.string.calling_activity), getString(R.string.search_activity));
                    intent.putExtra(getString(R.string.intent_user), mUserList.get(position));
                    startActivity(intent);
                }
            });
        }

/**
        private void hideSoftKeyboard(){
            if(getCurrentFocus() != null){
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        }
*/

        /**
         * BottomNavigationView setup
         */

    }




