package test.bwie.myapplication.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import test.bwie.myapplication.R;


public class AddSubView extends LinearLayout implements View.OnClickListener {
    private Context context;
    private TextView mTvSub;
    private TextView mTvNum;
    private TextView mTvAdd;
    private AddSubClickListener addSubClickListener;
    public AddSubView(Context context) {
        this(context, null);
    }
    public AddSubView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public AddSubView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();

    }
    private void initView() {
        View view = View.inflate(context, R.layout.add_sub_view_layout, this);
        mTvSub = (TextView) view.findViewById(R.id.tv_sub);
        mTvNum = (TextView) view.findViewById(R.id.tv_num);
        mTvAdd = (TextView) view.findViewById(R.id.tv_add);
        mTvSub.setOnClickListener(this);
        mTvAdd.setOnClickListener(this);
    }
    public void setAddSubClickListener(AddSubClickListener addSubClickListener) {
        this.addSubClickListener = addSubClickListener;
    }
    public void setNum(String num) {
        int i = Integer.parseInt(num);
        if (i > 9) {
            mTvAdd.setEnabled(false);
        } else if (i < 2) {
            mTvSub.setEnabled(false);
        }
        mTvNum.setText(num);
    }
    public String getNum() {
        return mTvNum.getText().toString();
    }
    @Override
    public void onClick(View view) {
        if (addSubClickListener != null){
            int num = Integer.parseInt(mTvNum.getText().toString());
            switch (view.getId()){
                case R.id.tv_add:
                    num++;
                    if (!mTvSub.isEnabled()) {
                        mTvSub.setEnabled(true);
                    }
                    if (num > 9) {
                        mTvAdd.setEnabled(false);
                    }
                    break;
                case R.id.tv_sub:
                    num--;
                    if (!mTvAdd.isEnabled()) {
                        mTvAdd.setEnabled(true);
                    }
                    if (num < 2) {
                        mTvSub.setEnabled(false);
                    }
                    break;
            }
            addSubClickListener.addClickListener(num);
            mTvNum.setText(Integer.toString(num));
        }
    }

    public interface AddSubClickListener {
        void addClickListener(int num);
    }

}
