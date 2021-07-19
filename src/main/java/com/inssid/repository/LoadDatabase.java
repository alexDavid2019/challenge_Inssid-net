package com.inssid.repository;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.inssid.entity.Cliente;
import com.inssid.entity.Producto;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class LoadDatabase {

	 @Bean
	 CommandLineRunner initClients(IClienteRepository repo) {
		 Set<Cliente> clients = new HashSet<Cliente>(){{
			    add(new Cliente("name1","ape1",123,"---"));
			    add(new Cliente("name2","ape2",456,"---"));
			    add(new Cliente("name3","ape3",789,"---"));
			}};
	        return args -> {
	            for (Cliente item:clients) {
	                repo.save(item);
	            }
	        };
	    }
	 
	 @Bean
	 CommandLineRunner initProducts(IProductoRepository repo) {
		 Set<Producto> products = new HashSet<Producto>(){{
			    add(new Producto("code1","marca1","modelo1",Float.valueOf("22.99"),true));
			    add(new Producto("code2","marca2","modelo2",Float.valueOf("31.00"),true));
			    add(new Producto("code3","marca3","modelo3",Float.valueOf("56.72"),true));
			}};
	        return args -> {
	            for (Producto item:products) {
	                repo.save(item);
	            }
	        };
	    }
}
