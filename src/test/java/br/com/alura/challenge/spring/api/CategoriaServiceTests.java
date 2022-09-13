package br.com.alura.challenge.spring.api;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.alura.challenge.spring.api.entity.Categoria;
import br.com.alura.challenge.spring.api.projection.dto.CategoriaDto;
import br.com.alura.challenge.spring.api.projection.view.CategoriaAndVideosView;
import br.com.alura.challenge.spring.api.projection.view.CategoriaListView;
import br.com.alura.challenge.spring.api.projection.view.CategoriaView;
import br.com.alura.challenge.spring.api.projection.view.VideoWithoutCategoriaView;
import br.com.alura.challenge.spring.api.repository.CategoriaRepository;
import br.com.alura.challenge.spring.api.service.CategoriaService;
import br.com.alura.challenge.spring.api.service.VideoService;
import br.com.alura.challenge.spring.api.util.Util;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import(value = { CategoriaService.class })
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CategoriaServiceTests {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	CategoriaRepository categoriaRepository;

	@MockBean
	VideoService videoService;

	CategoriaListView categoriaListView1 = new CategoriaListView() {
		public String getId() {
			return "782535f2-c315-4d0c-b08c-a0806c3711a2";
		}

		public String getTitulo() {
			return "BRANCO";
		}

		public String getCor() {
			return "#FFF";
		};
	};

	CategoriaListView categoriaListView2 = new CategoriaListView() {
		public String getId() {
			return "6b94f76f-db4a-47af-aa14-34a4bb52c16e";
		}

		public String getTitulo() {
			return "VERMELHO";
		}

		public String getCor() {
			return "#FF0000";
		};
	};

	CategoriaView categoriaView = new CategoriaView() {
		public LocalDateTime getDataCriacao() {
			return LocalDateTime.now();
		}

		public LocalDateTime getDataModificacao() {
			return LocalDateTime.now();
		}

		public String getId() {
			return "d22483ce-e4d2-4920-a08c-8ce733a51813";
		}

		public String getTitulo() {
			return "AMARELO";
		}

		public String getCor() {
			return "#fff000";
		}
	};

	CategoriaAndVideosView categoriaAndVideosView1 = new CategoriaAndVideosView() {
		public String getId() {
			return "3ad66d5a-06bf-4d2f-bd38-92916395b1b4";
		}

		public String getTitulo() {
			return "AZUL";
		}

		public String getCor() {
			return "#000FFF";
		}

		public LocalDateTime getDataCriacao() {
			return LocalDateTime.now();
		}

		public LocalDateTime getDataModificacao() {
			return LocalDateTime.now();
		}

		public List<VideoWithoutCategoriaView> getVideos() {
			return Arrays.asList(new VideoWithoutCategoriaView() {
				public String getId() {
					return "2f92cdfc-e7c4-40d3-88c4-64ddafb63384";
				}

				public String getTitulo() {
					return "GOOGLE";
				}

				public String getDescricao() {
					return "Buscador";
				}

				public String getUrl() {
					return "https://www.google.com/";
				}

				public String getThumbnailUrl() {
					return "https://www.google.com/";
				}

				public Long getTotalAprova() {
					return 100L;
				}

				public Long getTotalRejeita() {
					return 10L;
				}
			});
		}
	};

	CategoriaAndVideosView categoriaAndVideosView2 = new CategoriaAndVideosView() {
		public String getId() {
			return "5e3d954a-6307-4416-baf9-cf2a122b0c76";
		}

		public String getTitulo() {
			return "PRETO";
		}

		public String getCor() {
			return "#000";
		}

		public LocalDateTime getDataCriacao() {
			return LocalDateTime.now();
		}

		public LocalDateTime getDataModificacao() {
			return LocalDateTime.now();
		}

		public List<VideoWithoutCategoriaView> getVideos() {
			return new ArrayList<>();
		}
	};

	Categoria categoria = new Categoria("51ec6009-f46f-40ec-b102-206ff1ad8141", LocalDateTime.now(),
			LocalDateTime.now(), "VERDE", "#008000");

	CategoriaDto dto = new CategoriaDto("LARANJA", "#FFA500");

	Pageable pageable = PageRequest.of(0, 10);

	@Test
	void findAll_sucess() throws Exception {
		Page<CategoriaListView> categorias = Util
				.convertListToPage(Arrays.asList(categoriaListView1, categoriaListView2), pageable);

		Mockito.when(categoriaRepository.findAllByOrderByTitulo(pageable)).thenReturn(categorias);

		mockMvc.perform(MockMvcRequestBuilders.get("/categorias").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("numberOfElements", is(2)))
				.andExpect(jsonPath("content[1].titulo", is("VERMELHO")));
	}

	@Test
	void findAll_error() throws Exception {
		Mockito.when(categoriaRepository.findAllByOrderByTitulo(pageable))
				.thenReturn(Util.convertListToPage(new ArrayList<>(), pageable));

		mockMvc.perform(MockMvcRequestBuilders.get("/categorias").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(jsonPath("error", is("ResourceNotFoundException")));
	}

	@Test
	void findCategoria_success() throws Exception {
		Mockito.when(categoriaRepository.getCategoriaById(categoriaView.getId()))
				.thenReturn(Optional.of(categoriaView));

		mockMvc.perform(MockMvcRequestBuilders.get("/categorias/" + categoriaView.getId())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", notNullValue())).andExpect(jsonPath("$.titulo", is("AMARELO")));
	}

	@Test
	void findCategoria_error() throws Exception {
		Mockito.when(categoriaRepository.getCategoriaById("ID")).thenReturn(Optional.ofNullable(null));

		mockMvc.perform(MockMvcRequestBuilders.get("/categorias/ID").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(jsonPath("error", is("ResourceNotFoundException")));
	}

	@Test
	void findVideos_sucess() throws Exception {
		Mockito.when(categoriaRepository.getVideosById(categoriaAndVideosView1.getId()))
				.thenReturn(Optional.of(categoriaAndVideosView1));

		mockMvc.perform(MockMvcRequestBuilders.get("/categorias/" + categoriaAndVideosView1.getId() + "/videos")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("numberOfElements", is(1))).andExpect(jsonPath("content[0].titulo", is("GOOGLE")));
	}

	@Test
	void findVideos_errorCategoria() throws Exception {
		Mockito.when(categoriaRepository.getVideosById("ID")).thenReturn(Optional.ofNullable(null));

		mockMvc.perform(MockMvcRequestBuilders.get("/categorias/ID/videos").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(jsonPath("error", is("ResourceNotFoundException")));
	}

	@Test
	void findVideos_errorVideo() throws Exception {
		Mockito.when(categoriaRepository.getVideosById(categoriaAndVideosView2.getId()))
				.thenReturn(Optional.of(categoriaAndVideosView2));

		mockMvc.perform(MockMvcRequestBuilders.get("/categorias/" + categoriaAndVideosView2.getId() + "/videos")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
				.andExpect(jsonPath("message", is("Video inexistente.")));
	}

	@Test
	void remove_sucess() throws Exception {
		Mockito.when(categoriaRepository.findById(categoria.getId())).thenReturn(Optional.ofNullable(categoria));

		mockMvc.perform(MockMvcRequestBuilders.delete("/categorias/" + categoria.getId())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
	}

	@Test
	void remove_error() throws Exception {
		Mockito.when(categoriaRepository.findById("ID")).thenReturn(Optional.ofNullable(null));

		mockMvc.perform(MockMvcRequestBuilders.get("/categorias/ID").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(jsonPath("error", is("ResourceNotFoundException")));
	}

	@Test
	void updateAll_sucess() throws Exception {
		// TODO
		assertTrue(true);
	}

	void updateOnlyTitulo_sucess() throws Exception {
		// TODO
		assertTrue(true);
	}

	void updateOnlyCor_sucess() throws Exception {
		// TODO
		assertTrue(true);
	}

	@Test
	void update_errorBusiness() throws Exception {
		// TODO
		assertTrue(true);
	}

	@Test
	void update_errorCategoria() throws Exception {
		// TODO
		assertTrue(true);
	}

	@Test
	void create_sucess() throws Exception {
		// TODO
		assertTrue(true);
	}

	@Test
	void create_errorNotBlank() throws Exception {
		// TODO
		assertTrue(true);
	}

	@Test
	void createOrUpdate_errorJson() throws Exception {
		// TODO
		assertTrue(true);
	}

	@Test
	void createOrUpdate_errorUnique() throws Exception {
		// TODO
		assertTrue(true);
	}

	@Test
	void createOrUpdate_errorCor() throws Exception {
		// TODO
		assertTrue(true);
	}

}
