package gardeshgary.khozestan;

import gardeshgary.khozestan.model.Items;
import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsApi {
	@GET(StaticFields.GET_NEWS)
	Call<Items> getNews();
}
