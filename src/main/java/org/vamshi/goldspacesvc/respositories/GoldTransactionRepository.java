package org.vamshi.goldspacesvc.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vamshi.goldspacesvc.models.GoldTransaction;

public interface GoldTransactionRepository extends JpaRepository<GoldTransaction, Long> {
}
