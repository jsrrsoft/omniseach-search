package com.omniseach.omniseachsearchservice.repository;

import com.omniseach.omniseachsearchservice.model.Message;
import com.omniseach.omniseachsearchservice.dto.PagedResponse;

public interface MessageSearchRepository {

    PagedResponse<Message> search(String query, int page, int size);
}
