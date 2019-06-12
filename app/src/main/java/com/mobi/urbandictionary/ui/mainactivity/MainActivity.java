package com.mobi.urbandictionary.ui.mainactivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobi.urbandictionary.MyApplication;
import com.mobi.urbandictionary.R;
import com.mobi.urbandictionary.adapter.DefinitionAdapter;
import com.mobi.urbandictionary.data.DefinitionResponse;
import com.mobi.urbandictionary.data.ItemDefinition;
import com.mobi.urbandictionary.enums.SortMethod;
import com.mobi.urbandictionary.networkcall.ApiResponse;
import com.mobi.urbandictionary.ui.BaseActivity;
import com.mobi.urbandictionary.viewmodel.MainViewModel;
import com.mobi.urbandictionary.viewmodel.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Inject
    ViewModelFactory viewModelFactory;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edit_term)
    EditText editTerm;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.rv_definition)
    RecyclerView rvDefinitions;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    MainViewModel viewModel;

    DefinitionAdapter adapter;
    List<ItemDefinition> allDefinitions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ((MyApplication) getApplication()).getAppComponent().doInjection(this);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
        viewModel.getResponseLiveData().observe(this, this::consumeResponse);

        initRecyclerView();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (menu instanceof MenuBuilder) {
            ((MenuBuilder) menu).setOptionalIconsVisible(true);
        }
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_down_asc:
                sortItems(SortMethod.THUMB_DOWN_ASC);
                break;
            case R.id.menu_down_des:
                sortItems(SortMethod.THUMB_DOWN_DES);
                break;
            case R.id.menu_up_asc:
                sortItems(SortMethod.THUMB_UP_ASC);
                break;
            case R.id.menu_up_des:
                sortItems(SortMethod.THUMB_UP_DES);
                break;

            default:
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.btn_show_result})
    public void onViewClicked(View view) {
        // hide keyboard
        editTerm.onEditorAction(EditorInfo.IME_ACTION_DONE);

        if (!isValid(editTerm)) {
            return;
        }
        viewModel.getTermResult(getString(editTerm));
    }

    public void sortItems(SortMethod method) {
        if (allDefinitions.isEmpty()) {
            showStatusText(getString(R.string.no_item_for_sort));
            return;
        }
        ItemDefinition.sort(allDefinitions, method);
        adapter.notifyDataSetChanged();
        rvDefinitions.smoothScrollToPosition(0);
    }

    private void initRecyclerView() {
        if (adapter == null) {
            allDefinitions = new ArrayList<>();
            adapter = new DefinitionAdapter(allDefinitions);
            rvDefinitions.setLayoutManager(new LinearLayoutManager(this));
            rvDefinitions.setAdapter(adapter);
        }
    }

    private void consumeResponse(ApiResponse response) {
        switch (response.status) {
            case LOADING:
                showProgressBar();
                break;

            case SUCCESS:
                assert response.data != null;
                updateRecyclerView(response.data);
                break;

            case ERROR:
                if (response.error != null) {
                    if (!checkNetworkAvailability()) {
                        showStatusText(getString(R.string.network_error));
                        return;
                    }
                    showStatusText(response.error.getMessage());
                }
                break;

            default:

        }
    }

    private void updateRecyclerView(DefinitionResponse data) {
        if (data != null && !data.getList().isEmpty()) {
            showRecyclerView();
            allDefinitions.clear();
            adapter.notifyDataSetChanged();
            rvDefinitions.removeAllViews();

            allDefinitions.addAll(data.getList());
            adapter.notifyDataSetChanged();

            if (!checkNetworkAvailability()) {
                showStatusText(getString(R.string.cache));
                rvDefinitions.setVisibility(View.VISIBLE);
            }
        } else {
            showStatusText(getString(R.string.no_result_found));
        }

    }

    protected void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        tvStatus.setVisibility(View.GONE);
        rvDefinitions.setVisibility(View.GONE);
    }

    protected void showStatusText(String text) {
        progressBar.setVisibility(View.GONE);
        rvDefinitions.setVisibility(View.GONE);
        tvStatus.setVisibility(View.VISIBLE);
        tvStatus.setText(text);
    }

    protected void showRecyclerView() {
        progressBar.setVisibility(View.GONE);
        tvStatus.setVisibility(View.GONE);
        rvDefinitions.setVisibility(View.VISIBLE);
    }
}