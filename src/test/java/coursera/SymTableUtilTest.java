package coursera;

import coursera.exercise.SymTableUtil;
import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class SymTableUtilTest {
	@Test
	public void toUpperChar() {
		assertThat(SymTableUtil.toUpperChar(0)).isEqualTo('A');
		assertThat(SymTableUtil.toUpperChar(25)).isEqualTo('Z');
	}

	@Test
	public void fromUpperChar() {
		assertThat(SymTableUtil.fromUpperChar('A')).isEqualTo(0);
		assertThat(SymTableUtil.fromUpperChar('Z')).isEqualTo(25);
	}
}
