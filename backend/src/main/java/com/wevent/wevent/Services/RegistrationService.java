package com.wevent.wevent.Services;


import com.wevent.wevent.Email.EmailSender;
import com.wevent.wevent.Email.EmailValidator;
import com.wevent.wevent.Entities.Role;
import com.wevent.wevent.Entities.Utilisateur;
import com.wevent.wevent.Repositories.UserRepo;
import com.wevent.wevent.Request.LoginRequest;
import com.wevent.wevent.Request.RegistrationRequest;
import com.wevent.wevent.Token.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final EmailSender emailSender;
    private final ConfirmationTokenService confirmationTokenService;
    private EmailValidator emailValidator;
    BCryptPasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("email introuvable");
        }

        Collection<Role> roles = new ArrayList<>();
        Role clientRole = new Role(3L, "CLIENT");
        roles.add(clientRole);

        try {
            String token = userService.signUpUser(
                    new Utilisateur(
                            request.getPrenom(),
                            request.getNom(),
                            request.getEmail(),
                            roles,
                            request.getMotDePasse(),
                            request.getDateNaissance(),
                            request.getNumTel()
                    )
            );
            emailSender.send(request.getEmail(), buildEmail(request.getPrenom(), token));
            return token;
        } catch (IllegalArgumentException e) {
            // Handle the case when the password is null
            return "Error: Password cannot be null";
        }
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("Token Introuvable"));
        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("Email Déjà Confirmé");
        }
        LocalDateTime  expiredAt= confirmationToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token Expiré");
        }
        confirmationTokenService.setConfirmedAt(token);
        userService.enableUser(
                confirmationToken.getUtilisateur().getEmail());
        return "confirmed";
    }
    public String buildEmail(String name,String link) {
        return "<div style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; height: 100% !important; width: 100% !important; background-color: #FFFFFF; margin: 0; padding: 0;\">\r\n"
                + "<style type=\"text/css\">#outlook a {\r\n"
                + "          padding: 0;\r\n"
                + "      }\r\n"
                + "      .body{\r\n"
                + "          width: 100% !important;\r\n"
                + "          -webkit-text-size-adjust: 100%;\r\n"
                + "          -ms-text-size-adjust: 100%;\r\n"
                + "          margin: 0;\r\n"
                + "          padding: 0;\r\n"
                + "      }\r\n"
                + "      .ExternalClass {\r\n"
                + "          width:100%;\r\n"
                + "      }\r\n"
                + "      .ExternalClass,\r\n"
                + "      .ExternalClass p,\r\n"
                + "      .ExternalClass span,\r\n"
                + "      .ExternalClass font,\r\n"
                + "      .ExternalClass td,\r\n"
                + "      .ExternalClass div {\r\n"
                + "          line-height: 100%;\r\n"
                + "      }\r\n"
                + "      img {\r\n"
                + "          outline: none;\r\n"
                + "          text-decoration: none;\r\n"
                + "          -ms-interpolation-mode: bicubic;\r\n"
                + "      }\r\n"
                + "      a img {\r\n"
                + "          border: none;\r\n"
                + "      }\r\n"
                + "      p {\r\n"
                + "          margin: 1em 0;\r\n"
                + "      }\r\n"
                + "      table td {\r\n"
                + "          border-collapse: collapse;\r\n"
                + "      }\r\n"
                + "      /* hide unsubscribe from forwards*/\r\n"
                + "      blockquote .original-only, .WordSection1 .original-only {\r\n"
                + "        display: none !important;\r\n"
                + "      }\r\n"
                + "\r\n"
                + "      @media only screen and (max-width: 480px){\r\n"
                + "        body, table, td, p, a, li, blockquote{-webkit-text-size-adjust:none !important;} /* Prevent Webkit platforms from changing default text sizes */\r\n"
                + "                body{width:100% !important; min-width:100% !important;} /* Prevent iOS Mail from adding padding to the body */\r\n"
                + "\r\n"
                + "        #bodyCell{padding:10px !important;}\r\n"
                + "\r\n"
                + "        #templateContainer{\r\n"
                + "          max-width:600px !important;\r\n"
                + "          width:100% !important;\r\n"
                + "        }\r\n"
                + "\r\n"
                + "        h1{\r\n"
                + "          font-size:24px !important;\r\n"
                + "          line-height:100% !important;\r\n"
                + "        }\r\n"
                + "\r\n"
                + "        h2{\r\n"
                + "          font-size:20px !important;\r\n"
                + "          line-height:100% !important;\r\n"
                + "        }\r\n"
                + "\r\n"
                + "        h3{\r\n"
                + "          font-size:18px !important;\r\n"
                + "          line-height:100% !important;\r\n"
                + "        }\r\n"
                + "\r\n"
                + "        h4{\r\n"
                + "          font-size:16px !important;\r\n"
                + "          line-height:100% !important;\r\n"
                + "        }\r\n"
                + "\r\n"
                + "        #templatePreheader{display:none !important;} /* Hide the template preheader to save space */\r\n"
                + "\r\n"
                + "        #headerImage{\r\n"
                + "          height:auto !important;\r\n"
                + "          max-width:600px !important;\r\n"
                + "          width:100% !important;\r\n"
                + "        }\r\n"
                + "\r\n"
                + "        .headerContent{\r\n"
                + "          font-size:20px !important;\r\n"
                + "          line-height:125% !important;\r\n"
                + "        }\r\n"
                + "\r\n"
                + "        .bodyContent{\r\n"
                + "          font-size:18px !important;\r\n"
                + "          line-height:125% !important;\r\n"
                + "        }\r\n"
                + "\r\n"
                + "        .footerContent{\r\n"
                + "          font-size:14px !important;\r\n"
                + "          line-height:115% !important;\r\n"
                + "        }\r\n"
                + "\r\n"
                + "        .footerContent a{display:block !important;} /* Place footer social and utility links on their own lines, for easier access */\r\n"
                + "      }\r\n"
                + "</style>\r\n"
                + "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"bodyTable\" style=\"-ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; background-color: #FFFFFF; border-collapse: collapse !important; height: 100% !important; margin: 0; mso-table-lspace: 0pt; mso-table-rspace: 0pt; padding: 0; width: 100% !important\" width=\"100%\">\r\n"
                + "	<tbody>\r\n"
                + "		<tr>\r\n"
                + "			<td align=\"center\" id=\"bodyCell\" style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; mso-table-lspace: 0pt; mso-table-rspace: 0pt; height: 100% !important; width: 100% !important; padding: 20px;\" valign=\"top\"><!-- BEGIN TEMPLATE // -->\r\n"
                + "			<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"templateContainer\" style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-collapse: collapse !important; width: 600px; border: 1px solid #dddddd;\">\r\n"
                + "				<tbody>\r\n"
                + "					<tr>\r\n"
                + "						<td align=\"center\" style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" valign=\"top\"><!-- BEGIN PREHEADER // -->\r\n"
                + "						<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"templatePreheader\" style=\"-ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; background-color: #FFFFFF; border-bottom-color: #CCCCCC; border-bottom-style: solid; border-bottom-width: 1px; border-collapse: collapse !important; mso-table-lspace: 0pt; mso-table-rspace: 0pt\" width=\"100%\">\r\n"
                + "							<tbody>\r\n"
                + "								<tr>\r\n"
                + "									<td align=\"left\" class=\"preheaderContent\" pardot-region=\"preheader_content00\" style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #808080; font-family: Helvetica; font-size: 10px; line-height: 12.5px; text-align: left; padding: 10px 20px;\" valign=\"top\">EMAILING &nbsp;| NeoXam 2018</td>\r\n"
                + "									<td align=\"left\" class=\"preheaderContent\" pardot-region=\"preheader_content01\" style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #808080; font-family: Helvetica; font-size: 10px; line-height: 12.5px; text-align: left; padding: 10px 20px 10px 0;\" valign=\"top\" width=\"180\">Email not displaying correctly?<br>\r\n"
                + "									<a href=\"%%view_online%%\" style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; color: #606060; font-weight: normal; text-decoration: underline;\" target=\"_blank\">View it in your browser</a>.</td>\r\n"
                + "								</tr>\r\n"
                + "							</tbody>\r\n"
                + "						</table>\r\n"
                + "						<!-- // END PREHEADER --></td>\r\n"
                + "					</tr>\r\n"
                + "					<tr>\r\n"
                + "						<td align=\"center\" style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" valign=\"top\"><!-- BEGIN HEADER // -->\r\n"
                + "						<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"templateHeader\" style=\"-ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; background-color: #FFFFFF; border-bottom-color: #CCCCCC; border-bottom-style: solid; border-bottom-width: 1px; border-collapse: collapse !important; mso-table-lspace: 0pt; mso-table-rspace: 0pt\" width=\"100%\">\r\n"
                + "							<tbody>\r\n"
                + "								<tr pardot-repeatable=\"\" class=\"\">\r\n"
                + "									<td align=\"left\" class=\"headerContent\" pardot-region=\"header_image\" style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #505050; font-family: Helvetica; font-size: 20px; font-weight: bold; line-height: 20px; text-align: left; vertical-align: middle; padding: 0;\" valign=\"top\"><img alt=\"\" border=\"0\" height=\"160\" id=\"headerImage\" src=\"http://go.pardot.com/l/99632/2018-05-31/4xpqs5/99632/69008/Emailing_Template_Gen_2018_600px_v1.png\" style=\"max-width: 600px; outline: none; text-decoration: none; border-width: 0px; border-style: solid; width: 600px; height: 160px;\" width=\"600\"></td>\r\n"
                + "								</tr>\r\n"
                + "							</tbody>\r\n"
                + "						</table>\r\n"
                + "						<!-- // END HEADER --></td>\r\n"
                + "					</tr>\r\n"
                + "					<tr>\r\n"
                + "						<td align=\"center\" style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" valign=\"top\"><!-- BEGIN BODY // -->\r\n"
                + "						<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"templateBody\" style=\"-ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; background-color: #FFFFFF; border-bottom-color: #CCCCCC; border-bottom-style: solid; border-bottom-width: 1px; border-collapse: collapse !important; border-top-color: #FFFFFF; border-top-style: solid; border-top-width: 1px; mso-table-lspace: 0pt; mso-table-rspace: 0pt\" width=\"100%\">\r\n"
                + "							<tbody>\r\n"
                + "								<tr pardot-repeatable=\"\" class=\"\">\r\n"
                + "									<td align=\"left\" class=\"bodyContent\" pardot-data=\"\" pardot-region=\"body_content\" style=\"color: rgb(146, 145, 145); font-family: &quot;Century Gothic&quot;, Helvetica; font-size: 14px; line-height: 18px; text-align: left; padding: 20px;\" valign=\"top\">\r\n"
                + "									<h1 style=\"color: #0ed8b8 !important; display: block; font-family: 'Century Gothic', Helvetica;  font-size: 35px; font-style: normal; font-weight: bold; letter-spacing: normal; line-height: 40px; margin: 0; padding-bottom:5px; text-align: left\">Mail de vérification</h1>\r\n"
                + "\r\n"
                + "\r\n"
                + "\r\n"
                + "\r\n"
                + "									<h5 style=\"color: #22211f !important; display: block; font-family: 'Century Gothic', Helvetica;  font-size: 24px; font-style: normal; font-weight: bold; letter-spacing: normal; line-height: 32px; margin: 0; padding-bottom:20px; padding-top:20px;text-align: left\">Code de sécurité</h5>\r\n"
                + "									Bonjour <strong>"+name+"</strong>,<br>\r\n"
                + "									<br>\r\n"

                + "                  Saisissez ce code sur notre plateforme WEVENT pour activer votre compte.\r\n"
                + "                  \r\n"
                + "                  <br>\r\n"
                + "                  Vous êtes les bienvenues !\r\n"
                + "                  <strong><span style=\"color: #0ed8b8;\"> Ci-dessous se trouve votre code de sécurité: <br><br></span></strong><br>\r\n"
                + "									<br>\r\n"
                + "<h3 style\"color:#000000>"+link+"</h3>"
                + "<h3 style\"color:#000000>"+"Le lien expire dans 10 minutes. Vous devez vous reconnecter pour accéder à votre compte. Merci"+"</h3>"
                + "									\r\n"
                + "									\r\n"
                + "									Cordialement,<br>\r\n"
                + "									<br>\r\n"
                + "									<strong><span style=\"color: #22211f;\">NeoXam</span></strong><br>\r\n"
                + "									<h6 style=\"color: #0ed8b8 !important; display: block; font-family: 'Century Gothic', Helvetica;  font-size: 18px; font-style: normal; font-weight: bold; letter-spacing: normal; line-height: 20px; margin: 0;  padding-bottom:5px; padding-top:20px;text-align: left\">CONTACTS</h6>\r\n"
                + "									<a href=\"mailto:events@neoxam.com\" style=\"text-decoration: underline; color:#929191;\">events@neoxam.com</a><br>\r\n"
                + "                      website: <a href=\"mailto:events@neoxam.com\" style=\"text-decoration: underline; color:#929191;\">www.neoxam.com</a>\r\n"
                + "									    \r\n"
                + "									</td>\r\n"
                + "								</tr>\r\n"
                + "							</tbody>\r\n"
                + "						</table>\r\n"
                + "						<!-- // END BODY --></td>\r\n"
                + "					</tr>\r\n"
                + "					<tr>\r\n"
                + "						<td align=\"center\" style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" valign=\"top\"><!-- BEGIN FOOTER // -->\r\n"
                + "						<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"templateFooter\" style=\"-ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; background-color: #FFFFFF; border-collapse: collapse !important; border-top-color: #0ed8b8; border-top-style: solid; border-top-width: 2px; mso-table-lspace: 0pt; mso-table-rspace: 0pt\" width=\"100%\">\r\n"
                + "							<tbody>\r\n"
                + "								<tr pardot-removable=\"\" class=\"\">\r\n"
                + "									<td align=\"center\" class=\"footerContent\" pardot-region=\"footer_content00\" style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #808080; font-family: 'Century Gothic',Helvetica; font-size: 10px; line-height: 15px; text-align: center; padding: 20px;\" valign=\"top\"><a href=\"https://www.linkedin.com/company/neoxam\" style=\"border:none;background:none;\" target=\"_blank\"><img alt=\"Ico-LinkedIn\" border=\"0\" height=\"60\" src=\"http://go.pardot.com/l/99632/2018-05-31/4xpqsh/99632/69018/Ico_B_LinkedIn.png\" style=\"text-decoration: none; display: inline-block; border: 0px solid; background-image: none; width: 60px; height: 60px;\" width=\"60\"></a> &nbsp;<a href=\"https://twitter.com/neoxamsoftware\" style=\"border:none;background:none;\" target=\"_blank\"><img alt=\"Ico-Twitter\" border=\"0\" height=\"60\" src=\"http://go.pardot.com/l/99632/2018-05-31/4xpqsf/99632/69016/Ico_B_Twitter.png\" style=\"text-decoration: none; display: inline-block; border: 0px solid; background-image: none; width: 60px; height: 60px;\" width=\"60\"></a> &nbsp;<a href=\"https://www.youtube.com/channel/UCkXfTkZerN3TSlunbKQCgzg\" style=\"border:none;background:none;\" target=\"_blank\"><img alt=\"Ico-YouTube\" border=\"0\" height=\"60\" src=\"http://go.pardot.com/l/99632/2018-05-31/4xpqsm/99632/69022/Ico_B_YouTube.png\" style=\"text-decoration: none; display: inline-block; border: 0px solid; background-image: none; width: 60px; height: 60px;\" width=\"60\"></a> &nbsp;<a href=\"https://www.facebook.com/neoxam/\" style=\"border:none;background:none;\" target=\"_blank\"><img alt=\"Ico-Facebook\" border=\"0\" dir=\"\" height=\"60\" src=\"http://go.pardot.com/l/99632/2018-05-31/4xpqs7/99632/69010/Ico_B_Facebook.png\" style=\"text-decoration: none; display: inline-block; border: 0px solid; background-image: none; width: 60px; height: 60px;\" width=\"60\"></a> &nbsp;</td>\r\n"
                + "								</tr>\r\n"
                + "								<tr>\r\n"
                + "									<td align=\"left\" class=\"footerContent\" pardot-region=\"footer_content01\" style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #808080; font-family: 'Century Gothic',Helvetica; font-size: 11px; line-height: 12px; text-align: left; padding: 0 20px 20px;\" valign=\"top\">© 2018 NEOXAM • Trademark information: NeoXam and the NeoXam logo are trademarks of NeoXam. All other trade names are trademarks or registered trademarks of their respective holders.</td>\r\n"
                + "								</tr>\r\n"
                + "								<tr>\r\n"
                + "									<td align=\"left\" class=\"footerContent original-only\" pardot-region=\"footer_content02\" style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #808080; font-family: 'Century Gothic',Helvetica; font-size: 11px; line-height: 12px; text-align: left; padding: 10px 20px 20px;\" valign=\"top\"><a href=\"%%unsubscribe%%\" style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; color: #606060; font-weight: normal; text-decoration: underline;\">unsubscribe from all emails</a></td>\r\n"
                + "								</tr>\r\n"
                + "							</tbody>\r\n"
                + "						</table>\r\n"
                + "						<!-- // END FOOTER --></td>\r\n"
                + "					</tr>\r\n"
                + "				</tbody>\r\n"
                + "			</table>\r\n"
                + "			<!-- // END TEMPLATE --></td>\r\n"
                + "		</tr>\r\n"
                + "	</tbody>\r\n"
                + "</table>\r\n"
                + "<br>\r\n"
                + "<!--\r\n"
                + "          This email was originally designed by the wonderful folks at MailChimp and remixed by Pardot.\r\n"
                + "          It is licensed under CC BY-SA 3.0\r\n"
                + "        -->\r\n"
                + "      </div>\r\n"
                + "";
    }
    public String login(LoginRequest request) {
        if (userRepo.findByEmail(request.getEmail()).isPresent() &&  passwordEncoder
                .matches(request.getPassword(),userRepo.findByEmail(request.getEmail()).get().getMotDePasse()))
        {
            String token = UUID.randomUUID().toString();
            ConfirmationToken confirmationToken = new ConfirmationToken(
                    token,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(10),
                    userRepo.findByEmail(request.getEmail()).get()
            );
            confirmationTokenService.saveConfirmationToken(confirmationToken);
            LocalDateTime t = confirmationToken.getExpiresAt();
            emailSender.send(request.getEmail(),buildEmail(userRepo.findByEmail(request.getEmail()).get().getPrenom(), token));

            return "{"
                    + "Utilisateur connecté avec succès, voici le token de connexion\","+ token+"\","
                    +"\n\"email\" :\"" + userRepo.findByEmail(request.getEmail()).get().getEmail()+"\","
                    +"\n\"first_name\" : \"" + userRepo.findByEmail(request.getEmail()).get().getPrenom()+"\","
                    +"}";
        }
        throw new IllegalStateException(" Les identifiants de connexion sont eronnés ")	;
    }

    public void sendPasswordResetEmail(Utilisateur utilisateur, String resetToken) {
        String emailContent = "Dear " + utilisateur.getNom() + ",\n\n"
                + "You have requested to reset your password. Please click on the link below to reset your password:\n\n"
                + "Reset Password Link: http://localhost:4200/users/reset-password?token=" + resetToken + "\n\n"
                + "If you did not request this password reset, please ignore this email.\n\n"
                + "Best regards,\n"
                + "Your Application Team";

        emailSender.send(utilisateur.getEmail(), emailContent);
    }
}

