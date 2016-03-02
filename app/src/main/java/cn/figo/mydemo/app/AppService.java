package cn.figo.mydemo.app;


import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by SilenceDut on 2015/11/28.
 */
public class AppService{
    private static final AppService appService=new AppService();
    private static Gson mGson;
    private static EventBus mBus ;
//    private static Api api;
    private static ExecutorService sSingleThreadExecutor;
//    private Map<Integer,CompositeSubscription> mCompositeSubMap;

    private AppService(){}

    public void initService() {
        mBus = EventBus.getDefault();
        mGson = new Gson();
//        mCompositeSubMap=new HashMap<Integer,CompositeSubscription>();
        sSingleThreadExecutor= Executors.newSingleThreadExecutor();

        backGroundInit();
    }

    private void backGroundInit() {

    }

    public static EventBus getBus() {
        return mBus;
    }

    public void addCompositeSub(int taskId) {
//        CompositeSubscription compositeSubscription;
//        if(mCompositeSubMap.get(taskId)==null) {
//            compositeSubscription = new CompositeSubscription();
//            mCompositeSubMap.put(taskId, compositeSubscription);
//        }
    }

    public void removeCompositeSub(int taskId) {
//        CompositeSubscription compositeSubscription;
//        if(mCompositeSubMap!=null&& mCompositeSubMap.get(taskId)!=null){
//            compositeSubscription= mCompositeSubMap.get(taskId);
//            compositeSubscription.unsubscribe();
//            mCompositeSubMap.remove(taskId);
//        }
    }

//    public void initNews(int taskId,String type) {
//        getCompositeSubscription(taskId).add(RxNews.initNews(type));
//    }
//
//    public void updateNews(int taskId,String type) {
//        getCompositeSubscription(taskId).add(RxNews.updateNews(type));
//    }
//
//    public void loadMoreNews(int taskId,String type,String newsId) {
//        getCompositeSubscription(taskId).add(RxNews.loadMoreNews(type, newsId));
//    }
//
//    public void getNewsDetile(int taskId,String date,String detielId) {
//        getCompositeSubscription(taskId).add(RxNews.getNewsDetile(date, detielId));
//    }
//
//    public void initPerStat(int taskId,String  statKind) {
//        getCompositeSubscription(taskId).add(RxStats.initStat(statKind));
//    }
//
//    public void getPerStat(int taskId,String ...statKinds) {
//        getCompositeSubscription(taskId).add(RxStats.getPerStat(statKinds));
//    }
//
//    public void getTeamSort(int taskId) {
//        getCompositeSubscription(taskId).add(RxTeamSort.getTeams());
//    }
//
//    public void getGames(int taskId,String date) {
//        getCompositeSubscription(taskId).add(RxGames.getTeams(date));
//    }
//
//    private CompositeSubscription getCompositeSubscription(int taskId) {
//        CompositeSubscription compositeSubscription ;
//        if(mCompositeSubMap.get(taskId)==null) {
//            compositeSubscription = new CompositeSubscription();
//            mCompositeSubMap.put(taskId, compositeSubscription);
//        }else {
//            compositeSubscription= mCompositeSubMap.get(taskId);
//        }
//        return compositeSubscription;
//    }
//
//
    public static AppService getInstance() {
        return appService;
    }

//    public void getAsyn(Call responseCall, MyRetrofitCallBack myRetrofitCallBack) {
//        RetrofitClientManager.getAsyn(responseCall,myRetrofitCallBack);
//    }


//
//    public static EventBus getBus() {
//        return sBus;
//    }
//
//    public static NbaplusAPI getNbaplus() {
//        return sNbaplus;
//    }
//
//    public static NewsDetileAPI getNewsDetileApi() {
//        return sNewsDetileApi;
//    }
//
//    public static DBHelper getDBHelper() {
//        return sDBHelper;
//    }
//
//    public static Gson getGson() {
//        return sGson;
//    }
//
//    public static ExecutorService getSingleThreadExecutor(){
//        return sSingleThreadExecutor;
//    }

}
