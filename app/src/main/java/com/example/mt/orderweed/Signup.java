package com.example.mt.orderweed;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Signup.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Signup#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Signup extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Signup() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Signup.
     */
    // TODO: Rename and change types and number of parameters
    public static Signup newInstance(String param1, String param2) {
        Signup fragment = new Signup();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    static Button signup_btn,skip_btn;
    RelativeLayout Cancel,Continue;
    TextView cancel_txt,continue_txt,dialog_header;
    EditText name,mail,pass,confirm_pass;
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.fragment_signup, container, false);
        init_components();
        set_btns_typeface();

        skip_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewDialog alert = new ViewDialog();
                alert.showDialog(getActivity());
            }
        });

        validate_input(v);
        TextWatcher mTextWatcher = new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // check Fields For Empty Values
                validate_input(v);
            }
        };
        name.addTextChangedListener(mTextWatcher);
        mail.addTextChangedListener(mTextWatcher);
        pass.addTextChangedListener(mTextWatcher);
        confirm_pass.addTextChangedListener(mTextWatcher);

        signup_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                if (!pass.getText().toString().trim().equals(confirm_pass.getText().toString().trim()))
                {
                    show_password_error();
                }

                else
                {
                    startActivity(new Intent(getContext(),Home_Products.class));
                }
            }
        });
        return v;
    }

    void init_components()
    {
        signup_btn = (Button) v.findViewById(R.id.signup_btn);
        skip_btn = (Button) v.findViewById(R.id.skip_btn);
        name = (EditText)v.findViewById(R.id.name);
        mail = (EditText)v.findViewById(R.id.mail);
        pass = (EditText)v.findViewById(R.id.pass);
        confirm_pass = (EditText)v.findViewById(R.id.confirm_pass);
    }

    void set_btns_typeface()
    {
        Typeface typeface_btns = Typeface.createFromAsset(getActivity().getAssets(), "fonts/futura.otf");
        signup_btn.setTypeface(typeface_btns);
        skip_btn.setTypeface(typeface_btns);
    }

    void show_password_error()
    {
        pass.setError(getResources().getString(R.string.password_error));

    }

    void show_mail_exists_error()
    {
        mail.setError(getResources().getString(R.string.email_exists_error));
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    void validate_input(View v)
    {
        if( name.getText().toString().trim().length() < 4 || !Patterns.EMAIL_ADDRESS.matcher(mail.getText().toString()).matches() || pass.getText().toString().trim().length() < 4 || confirm_pass.getText().toString().trim().length() < 4 || pass.getText().toString().trim().length()!= confirm_pass.getText().toString().trim().length())
        {
            signup_btn.setEnabled(false);
            signup_btn.setAlpha(0.5f);
        }
        else
        {
            signup_btn.setEnabled(true);
            signup_btn.setAlpha(1f);
        }
    }

    public class ViewDialog {

        public void showDialog(Activity activity){
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.continue_without_signup_dialog);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            ////////// Set typeface of dialog components //////////
            cancel_txt = (TextView) dialog.findViewById(R.id.cancel_txt);
            continue_txt = (TextView) dialog.findViewById(R.id.continue_txt);
            dialog_header = (TextView) dialog.findViewById(R.id.dialog_header);

            Typeface typeface_btns = Typeface.createFromAsset(getActivity().getAssets(), "fonts/futura.otf");
            Typeface typeface_header = Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-Semibold.ttf");
            cancel_txt.setTypeface(typeface_btns);
            continue_txt.setTypeface(typeface_btns);
            dialog_header.setTypeface(typeface_header);

            Cancel = (RelativeLayout) dialog.findViewById(R.id.cancel_action);
            Continue = (RelativeLayout) dialog.findViewById(R.id.continue_action);
            Cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            Continue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    getContext().startActivity(new Intent(getContext(),Home_Products.class));
                }
            });

            dialog.show();
        }
    }
}
