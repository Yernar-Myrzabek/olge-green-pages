--
-- PostgreSQL database dump
--

\restrict 7b8unqUe46Rx21M1cuatETjA2xQmnTUI1HXsyUTKtzqKsNczoDNceNGNN3PJMwK

-- Dumped from database version 17.9 (Homebrew)
-- Dumped by pg_dump version 17.9 (Homebrew)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: postgis; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS postgis WITH SCHEMA public;


--
-- Name: EXTENSION postgis; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION postgis IS 'PostGIS geometry and geography spatial types and functions';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: categories; Type: TABLE; Schema: public; Owner: ernarmyrzabek
--

CREATE TABLE public.categories (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    parent_category character varying(100)
);


ALTER TABLE public.categories OWNER TO ernarmyrzabek;

--
-- Name: categories_id_seq; Type: SEQUENCE; Schema: public; Owner: ernarmyrzabek
--

CREATE SEQUENCE public.categories_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.categories_id_seq OWNER TO ernarmyrzabek;

--
-- Name: categories_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ernarmyrzabek
--

ALTER SEQUENCE public.categories_id_seq OWNED BY public.categories.id;


--
-- Name: organization_categories; Type: TABLE; Schema: public; Owner: ernarmyrzabek
--

CREATE TABLE public.organization_categories (
    organization_id integer NOT NULL,
    category_id integer NOT NULL
);


ALTER TABLE public.organization_categories OWNER TO ernarmyrzabek;

--
-- Name: organization_sdgs; Type: TABLE; Schema: public; Owner: ernarmyrzabek
--

CREATE TABLE public.organization_sdgs (
    organization_id integer NOT NULL,
    sdg_id integer NOT NULL
);


ALTER TABLE public.organization_sdgs OWNER TO ernarmyrzabek;

--
-- Name: organizations; Type: TABLE; Schema: public; Owner: ernarmyrzabek
--

CREATE TABLE public.organizations (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    description text,
    email character varying(255) NOT NULL,
    website character varying(255),
    country character varying(100) NOT NULL,
    city character varying(100),
    organization_type character varying(100),
    offering_type character varying(100),
    latitude numeric(9,6),
    longitude numeric(9,6),
    logo_url character varying(255),
    linkedin character varying(255),
    status character varying(20) DEFAULT 'pending'::character varying,
    created_at timestamp without time zone DEFAULT now(),
    updated_at timestamp without time zone DEFAULT now(),
    location public.geometry(Point,4326)
);


ALTER TABLE public.organizations OWNER TO ernarmyrzabek;

--
-- Name: organizations_id_seq; Type: SEQUENCE; Schema: public; Owner: ernarmyrzabek
--

CREATE SEQUENCE public.organizations_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.organizations_id_seq OWNER TO ernarmyrzabek;

--
-- Name: organizations_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ernarmyrzabek
--

ALTER SEQUENCE public.organizations_id_seq OWNED BY public.organizations.id;


--
-- Name: sdgs; Type: TABLE; Schema: public; Owner: ernarmyrzabek
--

CREATE TABLE public.sdgs (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.sdgs OWNER TO ernarmyrzabek;

--
-- Name: categories id; Type: DEFAULT; Schema: public; Owner: ernarmyrzabek
--

ALTER TABLE ONLY public.categories ALTER COLUMN id SET DEFAULT nextval('public.categories_id_seq'::regclass);


--
-- Name: organizations id; Type: DEFAULT; Schema: public; Owner: ernarmyrzabek
--

ALTER TABLE ONLY public.organizations ALTER COLUMN id SET DEFAULT nextval('public.organizations_id_seq'::regclass);


--
-- Name: categories categories_pkey; Type: CONSTRAINT; Schema: public; Owner: ernarmyrzabek
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (id);


--
-- Name: organization_categories organization_categories_pkey; Type: CONSTRAINT; Schema: public; Owner: ernarmyrzabek
--

ALTER TABLE ONLY public.organization_categories
    ADD CONSTRAINT organization_categories_pkey PRIMARY KEY (organization_id, category_id);


--
-- Name: organization_sdgs organization_sdgs_pkey; Type: CONSTRAINT; Schema: public; Owner: ernarmyrzabek
--

ALTER TABLE ONLY public.organization_sdgs
    ADD CONSTRAINT organization_sdgs_pkey PRIMARY KEY (organization_id, sdg_id);


--
-- Name: organizations organizations_pkey; Type: CONSTRAINT; Schema: public; Owner: ernarmyrzabek
--

ALTER TABLE ONLY public.organizations
    ADD CONSTRAINT organizations_pkey PRIMARY KEY (id);


--
-- Name: sdgs sdgs_pkey; Type: CONSTRAINT; Schema: public; Owner: ernarmyrzabek
--

ALTER TABLE ONLY public.sdgs
    ADD CONSTRAINT sdgs_pkey PRIMARY KEY (id);


--
-- Name: idx_organizations_location; Type: INDEX; Schema: public; Owner: ernarmyrzabek
--

CREATE INDEX idx_organizations_location ON public.organizations USING gist (location);


--
-- Name: idx_organizations_search; Type: INDEX; Schema: public; Owner: ernarmyrzabek
--

CREATE INDEX idx_organizations_search ON public.organizations USING gin (to_tsvector('english'::regconfig, (((((COALESCE(name, ''::character varying))::text || ' '::text) || COALESCE(description, ''::text)) || ' '::text) || (COALESCE(country, ''::character varying))::text)));


--
-- Name: organization_categories organization_categories_category_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ernarmyrzabek
--

ALTER TABLE ONLY public.organization_categories
    ADD CONSTRAINT organization_categories_category_id_fkey FOREIGN KEY (category_id) REFERENCES public.categories(id);


--
-- Name: organization_categories organization_categories_organization_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ernarmyrzabek
--

ALTER TABLE ONLY public.organization_categories
    ADD CONSTRAINT organization_categories_organization_id_fkey FOREIGN KEY (organization_id) REFERENCES public.organizations(id);


--
-- Name: organization_sdgs organization_sdgs_organization_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ernarmyrzabek
--

ALTER TABLE ONLY public.organization_sdgs
    ADD CONSTRAINT organization_sdgs_organization_id_fkey FOREIGN KEY (organization_id) REFERENCES public.organizations(id);


--
-- Name: organization_sdgs organization_sdgs_sdg_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ernarmyrzabek
--

ALTER TABLE ONLY public.organization_sdgs
    ADD CONSTRAINT organization_sdgs_sdg_id_fkey FOREIGN KEY (sdg_id) REFERENCES public.sdgs(id);


--
-- PostgreSQL database dump complete
--

\unrestrict 7b8unqUe46Rx21M1cuatETjA2xQmnTUI1HXsyUTKtzqKsNczoDNceNGNN3PJMwK

