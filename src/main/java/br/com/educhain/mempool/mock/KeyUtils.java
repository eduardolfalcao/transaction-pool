package br.com.educhain.mempool.mock;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class KeyUtils {

	private static final Logger LOGGER = Logger.getLogger(KeyUtils.class);
	private static final String KEYGEN_ALGORITHM = "DSA";

	public static KeyPair generateKeyPair(String walletOwner) {
		KeyPairGenerator keyPairGen;
		KeyPair pair = null;
		try {
			keyPairGen = KeyPairGenerator.getInstance(KEYGEN_ALGORITHM);
			LOGGER.debug("Algorithm used for key generation: " + keyPairGen.getAlgorithm());
			keyPairGen.initialize(2048);
			pair = keyPairGen.generateKeyPair();
			LOGGER.info("Keypair had just been generated.");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return pair;
	}

}
