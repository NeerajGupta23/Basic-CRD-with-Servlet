package in.product.neeraj.factory;

import in.product.neeraj.repository.Repository;

public class RepoFactory {
	private static Repository repository = null;

	public static Repository getRepository() {
		if (repository == null) {
			repository = new Repository();
		}

		return repository;
	}
}
