<?php

namespace App\Form;

use App\Entity\Serveurs;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;

class ServeursType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
        ->add('id',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        // ->add('ref',TextType::class,[
        //     'attr' => [
        //         'class' => 'form-control',
        //     ],
        // ])
        ->add('etat', ChoiceType::class, [
            'attr' => [
                'class' => 'form-select',
            ],
            'choices'  => [
            'Nouveau' => "Nouveau" ,
            'Supprimé'=>"Supprimé",
            'Modifié' => "Modifié",
            'Inchangé' => "Inchangé" ,
            
        ],
        ])
        ->add('platforme', ChoiceType::class, [
            'attr' => [
                'class' => 'form-select',
            ],
            'choices'  => [
            'Prod' => "Prod" ,
            'Pré-Prod'=>"Pré-Prod",
            
        ],
        ])
        ->add('hostname',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('fqdn',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('type', ChoiceType::class, [
            'attr' => [
                'class' => 'form-select',
            ],
            'choices'  => [
            'Autres' => "Autres" ,
            'Baie'=>"Baie",
            'Firewall' => "Firewall",
            'Load_Balancer' => "Load_Balancer" ,
            'Machine_Virtuelle' => "Machine_Virtuelle" ,
            'Proxy' => "Proxy" ,
            'Routeur' => "Routeur" ,
            'Serveur' => "Serveur" ,
            'Switch' => "Switch" ,
            
        ],
        ])
        ->add('modele',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('os', ChoiceType::class, [
            'attr' => [
                'class' => 'form-select',
            ],
            'choices'  => [
            'AIX' => "AIX" ,
            'HPUX'=>"HPUX",
            'Linux' => "Linux" ,
            'Solaris' => "Solaris",
            'Windows' => "Windows" ,
            
        ],
        ])
        ->add('verTechFirmware', ChoiceType::class, [
            'attr' => [
                'class' => 'form-select',
            ],
            'choices'  => [
            '2000' => "2000" ,
            '2003 Enterprise Edition 32 bits'=>"2003 Enterprise Edition 32 bits",
            '2003 Enterprise Edition 64 bits'=>"2003 Enterprise Edition 64 bits",
            '2003 Standard Edition 32 bits' => "2003 Standard Edition 32 bits" ,
            '2003 Standard Edition 64 bits' => "2003 Standard Edition 64 bits" ,
            
        ],
        ])
        ->add('cluster',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('ipSource',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('description',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('socleStandardOmu', ChoiceType::class, [
            'attr' => [
                'class' => 'form-select',
            ],
            'choices'  => [
            'Oui' => "Oui" ,
            'Non'=>"Non",
            
        ],
        ])
        ->add('complementsInformations',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('support',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Serveurs::class,
            'attr' => [
                // Ajoutez l'attribut "novalidate" au formulaire
                'novalidate' => 'novalidate',
            ],
        ]);
    }
}
