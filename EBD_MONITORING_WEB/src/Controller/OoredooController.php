<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class OoredooController extends AbstractController
{
    #[Route('/ooredoo', name: 'app_ooredoo')]
    public function index(): Response
    {
        return $this->render('ooredoo/index.html.twig', [
            'controller_name' => 'OoredooController',
        ]);
    }
}
