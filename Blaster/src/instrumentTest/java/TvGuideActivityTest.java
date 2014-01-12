import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.GridView;
package import com.matthijsweb.blaster.R;
import com.matthijsweb.blaster.TvGuideActivity;

/**
 * Created by Matthijs on 12-1-14.
 */
public class TvGuideActivityTest extends ActivityUnitTestCase<TvGuideActivity> {

    TvGuideActivity tvGuideActivity;

    GridView gridView = (GridView) findViewById(R.id.grid_view);

    public TvGuideActivityTest() {
        super(TvGuideActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        startActivity(new Intent(getInstrumentation().getTargetContext(), TvGuideActivity.class), null, null)));

        tvGuideActivity= (TvGuideActivity)getActivity();

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

}
