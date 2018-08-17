package com.cesar.bruno.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.cesar.bruno.cursomc.domain.Cliente;
import com.cesar.bruno.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);

	void sendEmail(SimpleMailMessage msg);

	void sendOrderConfirmationHtmlEmail(Pedido obj);

	void sendHtmlEmail(MimeMessage mm);
	
	void sendNewPasswordEmail(Cliente cliente, String newPass);

}
