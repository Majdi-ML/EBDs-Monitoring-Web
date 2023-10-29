<?php

namespace App\Form;

use App\Entity\TrapsSnmp;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;


class TrapsSnmp1Type extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
        ->add('id',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('ref',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
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
        ->add('refComposant',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('signification',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('versionSnmp', ChoiceType::class, [
            'attr' => [
                'class' => 'form-select',
            ],
            'choices'  => [
            'Version 1' => "Version 1" ,
            'Version 2'=>"Version 2",
        ],
        ])
        ->add('oid',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('specificTrap',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('variableBinding',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('criticite', ChoiceType::class, [
            'attr' => [
                'class' => 'form-select',
            ],
            'choices'  => [
            'Critique' => "Critique" ,
            'Majeure'=>"Majeure",
            'Normale' => "Normale",
            
        ],
        ])
        ->add('messageAlarme',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('instructions',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('acquittement',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('mibAssocie',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('objet',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('compelementInformation',TextType::class,[
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
            'data_class' => TrapsSnmp::class,
            'attr' => [
                // Ajoutez l'attribut "novalidate" au formulaire
                'novalidate' => 'novalidate',
            ],
        ]);
    }
}
