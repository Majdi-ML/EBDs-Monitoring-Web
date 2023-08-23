<?php

namespace App\Form;

use App\Entity\Cluster;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class Cluster1Type extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('id')
            ->add('ref')
            ->add('etat')
            ->add('nomDuRessourceGroupPackageServiceGuard')
            ->add('adresseIp')
            ->add('listeDesServeursConcernes')
            ->add('logicielCluster')
            ->add('version')
            ->add('mode')
            ->add('serveurActif')
            ->add('complementsInformations')
          
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Cluster::class,
            'attr' => [
                // Ajoutez l'attribut "novalidate" au formulaire
                'novalidate' => 'novalidate',
            ],
        ]);
    }
}
