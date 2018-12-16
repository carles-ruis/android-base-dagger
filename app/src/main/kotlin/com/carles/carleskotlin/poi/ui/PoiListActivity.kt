package com.carles.carleskotlin.poi.ui

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.DividerItemDecoration.VERTICAL
import com.carles.carleskotlin.R
import com.carles.carleskotlin.common.ui.BaseActivity
import com.carles.carleskotlin.poi.model.Poi
import kotlinx.android.synthetic.main.activity_poi_list.poilist_recyclerview
import kotlinx.android.synthetic.main.view_toolbar.toolbar

class PoiListActivity : BaseActivity<PoiListContract, PoiListView>(), PoiListView, PoiListAdapter.Listener {

    override val layoutResourceId = R.layout.activity_poi_list
    override fun getView(): PoiListView = this
    private val adapter = PoiListAdapter()

    override fun initViews() {
        toolbar.setTitle(R.string.poilist_title)
        adapter.listener = this
        poilist_recyclerview.addItemDecoration(DividerItemDecoration(this, VERTICAL))
        poilist_recyclerview.adapter = adapter
    }

    override fun displayPoiList(poiList: List<Poi>) {
        adapter.setItems(poiList)
    }

    override fun navigateToPoiDetail(id: String) {
        startActivity(PoiDetailActivity.newIntent(this, id))
    }

    override fun onPoiClicked(poi: Poi) {
        presenter.onPoiClicked(poi)
    }

}