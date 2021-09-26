# Jetty Embedded Hello World over HTTPS

Sample self-certifacate creation:

    $ <JDK bin folder path>keytool -keystore helloWorldSSL.jks -alias helloWorldSSL -keyalg RSA -keysize 2048 -sigalg SHA256withRSA -genkey -validity 7
    Enter keystore password:
    Keystore password is too short - must be at least 6 characters
    Enter keystore password:  password
    Re-enter new password: password
    What is your first and last name?
      \[Unknown\]:
    What is the name of your organizational unit?
      \[Unknown\]:
    What is the name of your organization?
      \[Unknown\]:
    What is the name of your City or Locality?
      \[Unknown\]:
    What is the name of your State or Province?
      \[Unknown\]:
    What is the two-letter country code for this unit?
      \[Unknown\]:
    Is CN=Unknown, OU=Unknown, O=Unknown, L=Unknown, ST=Unknown, C=Unknown correct?
      [no]:  yes

    Enter key password for <helloWorldSSL>
            (RETURN if same as keystore password):

    Warning:
    The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore helloWorldSSL.jks -destkeystore helloWorldSSL.jks -deststoretype pkcs12".
