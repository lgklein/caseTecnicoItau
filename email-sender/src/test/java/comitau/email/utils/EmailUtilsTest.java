package comitau.email.utils;

import org.junit.Assert;
import org.junit.Test;

import com.itau.email.utils.EmailUtils;

public class EmailUtilsTest {

	@Test
	public void testTakeContentTitleOK() {
		final String str1 = "<!DOCTYPE HTML><html xmlns:th=\"http://www.thymeleaf.org\"><head><title>Aviso de saldo devedor - Itaú</title></head></html>";
		Assert.assertEquals("Aviso de saldo devedor - Itaú", EmailUtils.takeFirstContentTitle(str1));
	}

	@Test
	public void test2TakeContentTitleOK() {
		final String str1 = "<html><head></head><body><div><table><tr><td><title>Titulo testes envio de e-mail</title></td></tr></table></div></body>";
		Assert.assertEquals("Titulo testes envio de e-mail", EmailUtils.takeFirstContentTitle(str1));
	}

	@Test
	public void testMultipleTitleTakeContentTitleOK() {
		final String str1 = "<html><head></head><body><div><table><tr><td><title>Titulo testes envio de e-mail</title></td></tr></table><title>Titulo testes 2</title></div><title>Titulo testes 3</title></body>";
		Assert.assertEquals("Titulo testes envio de e-mail", EmailUtils.takeFirstContentTitle(str1));
	}

	@Test
	public void testEspecialCharactersTakeContentTitleOK() {
		final String str1 = "<html><head><div><table><tr><td><title>¶Titulo tes¶tes envio de e-mail¶</title></td></tr></table></div></head>";
		Assert.assertEquals("¶Titulo tes¶tes envio de e-mail¶", EmailUtils.takeFirstContentTitle(str1));
	}
}