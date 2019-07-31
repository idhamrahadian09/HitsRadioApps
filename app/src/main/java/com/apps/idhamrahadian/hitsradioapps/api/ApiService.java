package com.apps.idhamrahadian.hitsradioapps.api;

import com.apps.idhamrahadian.hitsradioapps.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("tambah_prog.php")
    Call<ResponseModel> postProgram(@Field("prog_name") String prog_name,
                                    @Field("prog_desc") String prog_desc,
                                    @Field("prog_hours") String prog_hours,
                                    @Field("prog_day") String prog_day);

    /*Read*/
    @GET("lihat_prog.php")
    Call<ResponseModel> getProgram();
}
