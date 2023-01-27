package com.commarian.namegamet3.HomeMade.Extras;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.commarian.namegamet3.HomeMade.Classes.RecyclerAdapter;
import com.commarian.namegamet3.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.Profanity;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.lobbyName;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.newMessage;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.noMoMessages;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.staticServer;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.staticUsername;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.username0;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.username1;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.username2;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.username3;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.username4;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.username5;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.fragMute;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.hideKeyboardFrom;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.playSound;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MessageFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessageFrag extends Fragment {
    FirebaseDatabase rdb = FirebaseDatabase.getInstance();
    Handler handler = new Handler();

    static private final ArrayList<String> mNames = new ArrayList<>();
    static private final ArrayList<String> mMessages = new ArrayList<>();

    ValueEventListener listen0;
    ValueEventListener listen1;
    ValueEventListener listen2;
    ValueEventListener listen3;
    ValueEventListener listen4;
    ValueEventListener listen5;


    EditText etMess;
    AppCompatImageView sendBtn;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MessageFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MessageFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static MessageFrag newInstance(String param1, String param2) {
        MessageFrag fragment = new MessageFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_messaging, container, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState){
        if(requireActivity().getApplicationContext() != null && getView()!=null){
            etMess = getView().findViewById(R.id.etMessageBody2);
            sendBtn = getView().findViewById(R.id.sendMessageBtn2);
            CheckUsernames();
            etMess.setOnFocusChangeListener((v, hasFocus) -> {
                if(!hasFocus){
                    hideKeyboardFrom(requireActivity().getApplicationContext(), getView());
                }
            });
            sendBtn.setOnClickListener(v -> {
                playSound("click");
                String msg = etMess.getText().toString();
                if(!msg.isEmpty()) {
                    if(Profanity(msg)){
                        msg = msg.replace(msg.charAt(msg.length()-1), "*".charAt(0));
                        msg = msg.replace(msg.charAt(msg.length()-2), "*".charAt(0));
                        msg = msg.replace(msg.charAt(msg.length()-3), "*".charAt(0));
                    }
                    addMessage(getString(R.string.You) + ":", msg, true);
                    etMess.setText("");
                }
            });
            checkMessages();
            CheckToClose();

        }
    }

    private void checkMessages(){
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(getView() !=null){
                    initRecyclerView();
                    handler.postDelayed(this, 1000);
                }

            }
        });
    }


    public static void addMessage(String username, String message, Boolean me){
        if(mMessages.size() == 0){
            mNames.add(username);
            mMessages.add(message);
            if(!me){
                if(!fragMute){
                    playSound("msgSuccess");
                }
            }else{
                if(lobbyName != null && !lobbyName.equalsIgnoreCase("")){
                    FirebaseDatabase.getInstance().getReference(lobbyName).child(staticUsername).
                            child("Mess").setValue(message).addOnCompleteListener(task -> System.out.println("Message Added"));
                }
            }
        }else if (mMessages.size() == 1){
            if(!((mMessages.get(0)+mNames.get(0)).equalsIgnoreCase(message + username))) {
                mNames.add(username);
                mMessages.add(message);
                if(!me){
                    if(!fragMute){
                        playSound("msgSuccess");
                    }
                }else{
                    if(lobbyName != null && !lobbyName.equalsIgnoreCase("")){
                        FirebaseDatabase.getInstance().getReference(lobbyName).child(staticUsername).
                                child("Mess").setValue(message).addOnCompleteListener(task -> System.out.println("Message Added"));
                    }
                }
            }
        }else if (mMessages.size() == 2){
            if(!((mMessages.get(0)+mNames.get(0)).equalsIgnoreCase(message + username))
                    && !((mMessages.get(1)+mNames.get(1)).equalsIgnoreCase(message + username))) {
                mNames.add(username);
                mMessages.add(message);
                if(!me){
                    if(!fragMute){
                        playSound("msgSuccess");
                    }
                }else{
                    if(lobbyName != null && !lobbyName.equalsIgnoreCase("")){
                        FirebaseDatabase.getInstance().getReference(lobbyName).child(staticUsername).
                                child("Mess").setValue(message).addOnCompleteListener(task -> System.out.println("Message Added"));
                    }
                }
            }
        }else{
            if(!((mMessages.get(mMessages.size() - 1)+mNames.get(mMessages.size() - 1)).equalsIgnoreCase(message + username))
                    && !((mMessages.get(mMessages.size() - 2)+mNames.get(mMessages.size() - 2)).equalsIgnoreCase(message + username))
                    && !((mMessages.get(mMessages.size() - 3)+mNames.get(mMessages.size() - 3)).equalsIgnoreCase(message + username))) {
                mNames.add(username);
                mMessages.add(message);
                if(!me){
                    if(!fragMute){
                        playSound("msgSuccess");
                    }
                }else{
                    if(lobbyName != null && !lobbyName.equalsIgnoreCase("")){
                        FirebaseDatabase.getInstance().getReference(lobbyName).child(staticUsername).
                                child("Mess").setValue(message).addOnCompleteListener(task -> System.out.println("Message Added"));
                    }
                }
            }
        }

    }

    private void initRecyclerView(){
        LinearLayoutManager LLM = new LinearLayoutManager(requireView().getContext());
        LLM.setStackFromEnd(true);
        LLM.setReverseLayout(true);
        LLM.scrollToPosition(0);
        RecyclerView recyclerView = getView().findViewById(R.id.messageRecylcer);
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getView().getContext(), mNames, mMessages);
        recyclerAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
                super.onItemRangeChanged(positionStart, itemCount, payload);

            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                super.onItemRangeRemoved(positionStart, itemCount);
            }

            @Override
            public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                super.onItemRangeMoved(fromPosition, toPosition, itemCount);
            }
        });
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(LLM);
    }



    void ListenHandler(String username, ValueEventListener listener, boolean add){
        final String[] usernameSmall = {username};
        if(add){
            listener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.getValue() != null){
                        String snappy = snapshot.getValue().toString();
                        if(!snappy.equals("") && !snappy.equals(" ")){
                            if(usernameSmall[0].length() > 6){
                                usernameSmall[0] = usernameSmall[0].substring(0,6);
                            }
                            addMessage(usernameSmall[0] + ":", snappy, false);
                            newMessage = true;
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    System.out.println("Error in getting message");
                }
            };
            rdb.getReference(lobbyName).child(username).child("Mess").addValueEventListener(listener);
        }else{
            rdb.getReference(lobbyName).child(username).child("Mess").removeEventListener(listener);
        }


    }


    private void CheckToClose(){
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(noMoMessages){
                    checkMessages();
                    mNames.clear();
                    mMessages.clear();
                    u0L = false;
                    u1L = false;
                    u2L = false;
                    u3L = false;
                    u4L = false;
                    u5L = false;
                    if(staticServer){
                        rdb.getReference(lobbyName).removeValue().addOnCompleteListener(task -> System.out.println("Removed values from default db"));
                    }

                }else {
                    //System.out.println(" NOT CLOSING MESSAGEFRAG");
                    handler.postDelayed(this, 3000);
                }
            }
        });
    }

    boolean u0L = false;
    boolean u1L = false;
    boolean u2L = false;
    boolean u3L = false;
    boolean u4L = false;
    boolean u5L = false;


    void CheckUsernames(){
        if(!noMoMessages){
                if (username0.length() < 2 && u0L) {
                    u0L = false;
                }else if(username0.length() > 2 && !u0L && !username0.equals(staticUsername)) {
                    u0L = true;
                    ListenHandler(username0, listen0, true);
                }
                if (username1.length() < 2 && u1L) {
                    u1L = false;
                }else if(username1.length() > 2 && !u1L && !username1.equals(staticUsername)) {
                    u1L = true;
                    ListenHandler(username1, listen1, true);
                }
                if (username2.length() < 2 && u2L) {
                    u2L = false;
                }else if(username2.length() > 2 && !u2L && !username2.equals(staticUsername)) {
                    u2L = true;
                    ListenHandler(username2, listen2, true);
                }
                if (username3.length() < 2 && u3L) {
                    u3L = false;
                }else if(username3.length() > 2 && !u3L && !username3.equals(staticUsername)) {
                    u3L = true;
                    ListenHandler(username3, listen3, true);
                }
                if (username4.length() < 2 && u4L) {
                    u4L = false;
                }else if(username4.length() > 2 && !u4L && !username4.equals(staticUsername)) {
                    u4L = true;
                    ListenHandler(username4, listen4, true);
                }
                if (username5.length() < 2 && u5L) {
                    u5L = false;
                }else if(username5.length() > 2 && !u5L && !username5.equals(staticUsername)) {
                    u5L = true;
                    ListenHandler(username5, listen5, true);
                }
            }

        if(!noMoMessages) {
            handler.postDelayed(this::CheckUsernames, 3000);
        }
    }

}